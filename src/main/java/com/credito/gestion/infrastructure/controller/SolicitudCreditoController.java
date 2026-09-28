package com.credito.gestion.infrastructure.controller;

import com.credito.gestion.domain.model.SolicitudCredito;
import com.credito.gestion.domain.port.SolicitudCreditoRepository;
import com.credito.gestion.application.usecase.ProcesarSolicitudCreditoUseCase;
import com.credito.gestion.infrastructure.exception.SolicitudDuplicadaException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/solicitudes")
@Tag(name = "Solicitudes de Crédito", description = "Endpoints para la gestión de solicitudes de crédito")
public class SolicitudCreditoController {

    private static final Logger log = LoggerFactory.getLogger(SolicitudCreditoController.class);

    private final SolicitudCreditoRepository repository;
    private final ProcesarSolicitudCreditoUseCase procesarUseCase;

    public SolicitudCreditoController(
            SolicitudCreditoRepository repository,
            ProcesarSolicitudCreditoUseCase procesarUseCase) {
        this.repository = repository;
        this.procesarUseCase = procesarUseCase;
    }

    @PostMapping
    @Operation(summary = "Crear solicitud de crédito", description = "Registra una nueva solicitud de crédito en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Solicitud creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "409", description = "Solicitud duplicada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<?> crearSolicitud(
            @Parameter(description = "Datos de la solicitud de crédito") @Valid @RequestBody SolicitudCreditoRequest request) {

        log.info("Recibida solicitud de crédito para documento: {}", request.numeroDocumento());

        if (request.numeroOperacion() == null || request.numeroOperacion().isBlank()) {
            request = new SolicitudCreditoRequest(
                    UUID.randomUUID().toString(),
                    request.tipoDocumento(),
                    request.numeroDocumento(),
                    request.monto(),
                    request.plazo(),
                    request.tipoCredito(),
                    request.tasaInteres()
            );
        }

        UUID numeroOperacion = UUID.fromString(request.numeroOperacion());

        var existente = repository.findByNumeroOperacion(numeroOperacion);
        if (existente.isPresent()) {
            log.warn("Solicitud duplicada detectada para numeroOperacion: {}", numeroOperacion);
            throw new SolicitudDuplicadaException("Ya existe una solicitud con el número de operación: " + numeroOperacion);
        }

        SolicitudCredito solicitud = new SolicitudCredito(
                numeroOperacion,
                request.tipoDocumento(),
                request.numeroDocumento(),
                request.monto(),
                request.plazo(),
                request.tipoCredito(),
                request.tasaInteres(),
                "PENDIENTE",
                null,
                null,
                null,
                null,
                java.time.LocalDateTime.now()
        );

        SolicitudCredito guardada = repository.save(solicitud);
        log.info("Solicitud {} guardada exitosamente", guardada.numeroOperacion());

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "numeroOperacion", guardada.numeroOperacion().toString(),
                "estado", guardada.estado(),
                "mensaje", "Solicitud de crédito creada exitosamente"
        ));
    }

    @GetMapping("/{numeroOperacion}")
    @Operation(summary = "Consultar solicitud", description = "Obtiene los detalles de una solicitud por número de operación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud encontrada"),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada")
    })
    public ResponseEntity<?> consultarSolicitud(
            @Parameter(description = "Número de operación de la solicitud") @PathVariable String numeroOperacion) {

        UUID operacion = UUID.fromString(numeroOperacion);
        var solicitud = repository.findByNumeroOperacion(operacion);

        if (solicitud.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Solicitud no encontrada"));
        }

        SolicitudCredito s = solicitud.get();
        return ResponseEntity.ok(Map.of(
                "numeroOperacion", s.numeroOperacion().toString(),
                "tipoDocumento", s.tipoDocumento(),
                "numeroDocumento", s.numeroDocumento(),
                "monto", s.monto().toString(),
                "plazo", s.plazo().toString(),
                "tipoCredito", s.tipoCredito(),
                "tasaInteres", s.tasaInteres().toString(),
                "estado", s.estado(),
                "resultadoAntifraude", s.resultadoAntifraude() != null ? s.resultadoAntifraude() : "N/A",
                "resultadoBuro", s.resultadoBuro() != null ? s.resultadoBuro() : "N/A",
                "resultadoCore", s.resultadoCore() != null ? s.resultadoCore() : "N/A",
                "observaciones", s.observaciones() != null ? s.observaciones() : ""
        ));
    }

    @PostMapping("/{numeroOperacion}/procesar")
    @Operation(summary = "Procesar solicitud", description = "Inicia el procesamiento de una solicitud de crédito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Procesamiento iniciado"),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada")
    })
    public ResponseEntity<?> procesarSolicitud(
            @Parameter(description = "Número de operación de la solicitud") @PathVariable String numeroOperacion) {

        UUID operacion = UUID.fromString(numeroOperacion);
        var solicitud = repository.findByNumeroOperacion(operacion);

        if (solicitud.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Solicitud no encontrada"));
        }

        try {
            SolicitudCredito result = procesarUseCase.ejecutar(solicitud.get());
            return ResponseEntity.ok(Map.of(
                    "numeroOperacion", result.numeroOperacion().toString(),
                    "estado", result.estado(),
                    "resultadoAntifraude", result.resultadoAntifraude() != null ? result.resultadoAntifraude() : "N/A",
                    "resultadoBuro", result.resultadoBuro() != null ? result.resultadoBuro() : "N/A",
                    "resultadoCore", result.resultadoCore() != null ? result.resultadoCore() : "N/A"
            ));
        } catch (Exception e) {
            log.error("Error al procesar solicitud {}: {}", numeroOperacion, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al procesar solicitud: " + e.getMessage()));
        }
    }

    public record SolicitudCreditoRequest(
            String numeroOperacion,
            String tipoDocumento,
            String numeroDocumento,
            String monto,
            Integer plazo,
            String tipoCredito,
            String tasaInteres
    ) {}
}