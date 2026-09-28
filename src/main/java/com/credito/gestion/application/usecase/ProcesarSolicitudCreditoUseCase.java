package com.credito.gestion.application.usecase;


import com.credito.gestion.infrastructure.exception.SolicitudDuplicadaException;
import com.credito.gestion.domain.model.SolicitudCredito;
import com.credito.gestion.domain.port.AntifraudeService;
import com.credito.gestion.domain.port.BuroRiesgosService;
import com.credito.gestion.domain.port.CoreBancarioService;
import com.credito.gestion.domain.port.SolicitudCreditoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Caso de uso principal para el procesamiento de solicitudes de crédito.
 * Orchestrates las interacciones con los servicios externos: antifraude,
 * buró de riesgos y core bancario.
 * 
 * Flujo principal:
 * 1. Verificar idempotencia mediante número de operación
 * 2. Evaluar riesgo de fraude
 * 3. Consultar buró de riesgos
 * 4. Registrar en core bancario
 * 5. Confirmar operación si todas las validaciones pasan
 */
@Service
public class ProcesarSolicitudCreditoUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ProcesarSolicitudCreditoUseCase.class);
    private static final String RESULTADO_APROBADO = "APROBADO";
    private static final String RESULTADO_RECHAZADO = "RECHAZADO";
    private static final String RESULTADO_REVISION = "REVISION";

    private final SolicitudCreditoRepository solicitudRepository;
    private final AntifraudeService antifraudeService;
    private final BuroRiesgosService buroRiesgosService;
    private final CoreBancarioService coreBancarioService;

    public ProcesarSolicitudCreditoUseCase(
            SolicitudCreditoRepository solicitudRepository,
            AntifraudeService antifraudeService,
            BuroRiesgosService buroRiesgosService,
            CoreBancarioService coreBancarioService) {
        this.solicitudRepository = solicitudRepository;
        this.antifraudeService = antifraudeService;
        this.buroRiesgosService = buroRiesgosService;
        this.coreBancarioService = coreBancarioService;
    }

    /**
     * Procesa una solicitud de crédito ejecutando el flujo completo de validación.
     *
     * @param solicitud la solicitud de crédito a procesar
     * @return la solicitud procesada con los resultados de cada validación
     */
    public SolicitudCredito ejecutar(SolicitudCredito solicitud) {
        logger.info("Iniciando procesamiento de solicitud: numeroOperacion={}", 
                solicitud.numeroOperacion());

        UUID numeroOperacion = solicitud.numeroOperacion();

        verificarIdempotencia(numeroOperacion, solicitud.tipoDocumento(), solicitud.numeroDocumento());

        SolicitudCredito solicitudConAntifraude = evaluarAntifraude(solicitud);

        if (RESULTADO_RECHAZADO.equals(solicitudConAntifraude.resultadoAntifraude())) {
            logger.warn("Solicitud rechazada por antifraude: numeroOperacion={}", numeroOperacion);
            return guardarSolicitud(solicitudConAntifraude);
        }

        SolicitudCredito solicitudConBuro = consultarBuroRiesgos(solicitudConAntifraude);

        if (RESULTADO_RECHAZADO.equals(solicitudConBuro.resultadoBuro())) {
            logger.warn("Solicitud rechazada por buró de riesgos: numeroOperacion={}", numeroOperacion);
            return guardarSolicitud(solicitudConBuro);
        }

        SolicitudCredito solicitudConCore = registrarEnCore(solicitudConBuro);

        if (RESULTADO_RECHAZADO.equals(solicitudConCore.resultadoCore())) {
            logger.error("Error al registrar en core bancario: numeroOperacion={}", numeroOperacion);
            return guardarSolicitud(solicitudConCore);
        }

        SolicitudCredito solicitudFinal = confirmarEnCore(solicitudConCore);
        logger.info("Solicitud procesada exitosamente: numeroOperacion={}, resultado={}", 
                numeroOperacion, solicitudFinal.resultadoCore());

        return guardarSolicitud(solicitudFinal);
    }

    private void verificarIdempotencia(UUID numeroOperacion, String tipoDocumento, String numeroDocumento) {
        solicitudRepository.findByNumeroOperacion(numeroOperacion)
            .ifPresent(s -> {
                logger.info("Solicitud duplicada detectada por numeroOperacion: {}", numeroOperacion);
                throw new com.credito.gestion.infrastructure.exception.SolicitudDuplicadaException(
                    "Ya existe una solicitud con el número de operación: " + numeroOperacion);
            });

        solicitudRepository.findByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento)
            .ifPresent(s -> {
                logger.info("Solicitud duplicada detectada por documento: tipo={}, numero={}", 
                        tipoDocumento, numeroDocumento);
                throw new com.credito.gestion.infrastructure.exception.SolicitudDuplicadaException(
                    "Ya existe una solicitud para el documento: " + tipoDocumento + " " + numeroDocumento);
            });
    }

    private SolicitudCredito evaluarAntifraude(SolicitudCredito solicitud) {
        try {
            logger.info("Evaluando antifraude para solicitud: {}", solicitud.numeroOperacion());
            String resultado = antifraudeService.evaluarRiesgo(solicitud).get();
            logger.info("Resultado antifraude: numeroOperacion={}, resultado={}", 
                    solicitud.numeroOperacion(), resultado);
            return solicitud.conResultadoAntifraude(resultado);
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error en servicio antifraude: numeroOperacion={}, error={}", 
                    solicitud.numeroOperacion(), e.getMessage());
            return solicitud.conResultadoAntifraude(RESULTADO_REVISION)
                    .conObservaciones("Error en evaluación antifraude: " + e.getMessage());
        }
    }

    private SolicitudCredito consultarBuroRiesgos(SolicitudCredito solicitud) {
        try {
            logger.info("Consultando buró de riesgos para solicitud: {}", solicitud.numeroOperacion());
            CompletableFuture<String> consultaBuro = buroRiesgosService.consultarBuro(solicitud);
            CompletableFuture<Integer> scoreFuture = buroRiesgosService.obtenerScore(
                    solicitud.tipoDocumento(), solicitud.numeroDocumento());

            String resultadoBuro = consultaBuro.get();
            Integer score = scoreFuture.get();

            logger.info("Resultado buró: numeroOperacion={}, resultado={}, score={}", 
                    solicitud.numeroOperacion(), resultadoBuro, score);

            String observaciones = String.format("Score crediticio: %d", score);
            return solicitud.conResultadoBuro(resultadoBuro).conObservaciones(observaciones);
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error en servicio de buró de riesgos: numeroOperacion={}, error={}", 
                    solicitud.numeroOperacion(), e.getMessage());
            return solicitud.conResultadoBuro(RESULTADO_REVISION)
                    .conObservaciones("Timeout al consultar buró de riesgos: " + e.getMessage());
        }
    }

    private SolicitudCredito registrarEnCore(SolicitudCredito solicitud) {
        try {
            logger.info("Registrando en core bancario: numeroOperacion={}, monto={}", 
                    solicitud.numeroOperacion(), solicitud.monto());
            String resultado = coreBancarioService.registrarOperacion(
                    solicitud.numeroOperacion(), 
                    solicitud.monto(), 
                    solicitud).get();
            logger.info("Resultado registro core: numeroOperacion={}, resultado={}", 
                    solicitud.numeroOperacion(), resultado);
            return solicitud.conResultadoCore(resultado);
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error al registrar en core bancario: numeroOperacion={}, error={}", 
                    solicitud.numeroOperacion(), e.getMessage());
            return solicitud.conResultadoCore(RESULTADO_RECHAZADO)
                    .conObservaciones("Error al registrar en core: " + e.getMessage());
        }
    }

    private SolicitudCredito confirmarEnCore(SolicitudCredito solicitud) {
        try {
            logger.info("Confirmando en core bancario: numeroOperacion={}", solicitud.numeroOperacion());
            String resultado = coreBancarioService.confirmarOperacion(solicitud.numeroOperacion()).get();
            logger.info("Resultado confirmación core: numeroOperacion={}, resultado={}", 
                    solicitud.numeroOperacion(), resultado);
            return solicitud.conResultadoCore(resultado);
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error al confirmar en core bancario: numeroOperacion={}, error={}", 
                    solicitud.numeroOperacion(), e.getMessage());
            try {
                coreBancarioService.cancelarOperacion(
                        solicitud.numeroOperacion(), 
                        "Error en confirmación: " + e.getMessage()).get();
            } catch (Exception cancelError) {
                logger.error("Error al intentar cancelar operación: numeroOperacion={}, error={}",
                        solicitud.numeroOperacion(), cancelError.getMessage());
            }
            return solicitud.conResultadoCore(RESULTADO_RECHAZADO)
                    .conObservaciones("Error al confirmar en core: " + e.getMessage());
        }
    }

    private SolicitudCredito guardarSolicitud(SolicitudCredito solicitud) {
        logger.info("Guardando solicitud: numeroOperacion={}", solicitud.numeroOperacion());
        return solicitudRepository.save(solicitud);
    }
}