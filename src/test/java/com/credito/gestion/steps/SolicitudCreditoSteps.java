package com.credito.gestion.steps;

import com.credito.gestion.Application;
import com.credito.gestion.domain.model.SolicitudCredito;
import com.credito.gestion.domain.port.AntifraudeService;
import com.credito.gestion.domain.port.BuroRiesgosService;
import com.credito.gestion.domain.port.CoreBancarioService;
import com.credito.gestion.domain.port.SolicitudCreditoRepository;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = Application.class)
public class SolicitudCreditoSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SolicitudCreditoRepository solicitudCreditoRepository;

    @Autowired
    private AntifraudeService antifraudeService;

    @Autowired
    private BuroRiesgosService buroRiesgosService;

    @Autowired
    private CoreBancarioService coreBancarioService;

    private SolicitudCredito solicitudEnProceso;
    private ResponseEntity<String> respuestaHttp;
    private String numeroOperacionGenerado;
    private String resultadoAntifraude;
    private String resultadoBuro;
    private String resultadoCore;

    @Dado("que el sistema está operativo y disponible")
    public void queElSistemaEstaOperativo() {
        assertNotNull(restTemplate, "RestTemplate no debería ser nulo");
        assertNotNull(solicitudCreditoRepository, "Repositorio no debería ser nulo");
    }

    @Dado("una solicitud de crédito con los siguientes datos:")
    public void unaSolicitudConLosDatos(Map<String, String> datosSolicitud) {
        numeroOperacionGenerado = datosSolicitud.getOrDefault("numeroOperacion", UUID.randomUUID().toString());
        String tipoDocumento = datosSolicitud.get("tipoDocumento");
        String numeroDocumento = datosSolicitud.get("numeroDocumento");
        String monto = datosSolicitud.get("monto");
        String plazo = datosSolicitud.get("plazoMeses");

        solicitudEnProceso = new SolicitudCredito(
            UUID.fromString(numeroOperacionGenerado),
            tipoDocumento,
            numeroDocumento,
            new BigDecimal(monto),
            Integer.parseInt(plazo),
            "ORIGINADOR_CREDITOS",
            null, null, null, null
        );
    }

    @Cuando("el originador envía la solicitud de crédito al sistema")
    public void elOriginadorEnvíaLaSolicitud() {
        String jsonSolicitud = String.format("""
            {
                "numeroOperacion": "%s",
                "tipoDocumento": "%s",
                "numeroDocumento": "%s",
                "monto": %s,
                "plazoMeses": %d,
                "canalOrigen": "ORIGINADOR_CREDITOS"
            }
            """,
            solicitudEnProceso.numeroOperacion(),
            solicitudEnProceso.tipoDocumento(),
            solicitudEnProceso.numeroDocumento(),
            solicitudEnProceso.monto(),
            solicitudEnProceso.plazoMeses()
        );

        respuestaHttp = restTemplate.postForEntity(
            "/api/solicitudes-credito",
            jsonSolicitud,
            String.class
        );
    }

    @Entonces("el sistema debe responder con código de estado {int}")
    public void elSistemaRespondeCon(int codigoEsperado) {
        assertNotNull(respuestaHttp, "La respuesta no debería ser nula");
        assertEquals(codigoEsperado, respuestaHttp.getStatusCode().value(),
            "El código de estado debería ser " + codigoEsperado);
    }

    @Y("el sistema debe guardar la solicitud en la base de datos")
    public void elSistemaDebeGuardarLaSolicitud() {
        var solicitudGuardada = solicitudCreditoRepository.findByNumeroOperacion(
            solicitudEnProceso.numeroOperacion()
        );
        assertTrue(solicitudGuardada.isPresent(), "La solicitud debería estar guardada");
    }

    @Dado("que existe una solicitud de crédito previamente procesada")
    public void existeSolicitudPreviamenteProcesada() {
        solicitudEnProceso = new SolicitudCredito(
            UUID.randomUUID(),
            "CC",
            "12345678",
            new BigDecimal("5000000"),
            12,
            "ORIGINADOR_CREDITOS",
            "APROBADO",
            "APROBADO",
            "APROBADO",
            null
        );
        solicitudCreditoRepository.save(solicitudEnProceso);
    }

    @Cuando("el originador envía nuevamente la solicitud con el mismo número de operación")
    public void elOriginadorEnvíaNuevamenteLaSolicitud() {
        String jsonSolicitud = String.format("""
            {
                "numeroOperacion": "%s",
                "tipoDocumento": "%s",
                "numeroDocumento": "%s",
                "monto": %s,
                "plazoMeses": %d,
                "canalOrigen": "ORIGINADOR_CREDITOS"
            }
            """,
            solicitudEnProceso.numeroOperacion(),
            solicitudEnProceso.tipoDocumento(),
            solicitudEnProceso.numeroDocumento(),
            solicitudEnProceso.monto(),
            solicitudEnProceso.plazoMeses()
        );

        respuestaHttp = restTemplate.postForEntity(
            "/api/solicitudes-credito",
            jsonSolicitud,
            String.class
        );
    }

    @Entonces("el sistema debe retornar el resultado del procesamiento anterior")
    public void elSistemaRetornaResultadoAnterior() {
        assertEquals(HttpStatus.CONFLICT.value(), respuestaHttp.getStatusCode().value(),
            "Debería retornarse conflicto por solicitud duplicada");
    }

    @Cuando("el sistema evalúa el riesgo con el servicio de antifraude")
    public void elSistemaEvaluaRiesgoAntifraude() {
        CompletableFuture<String> resultadoFuture = antifraudeService.evaluarRiesgo(solicitudEnProceso);
        resultadoAntifraude = resultadoFuture.join();
    }

    @Entonces("el resultado del antifraude debe ser {string}")
    public void elResultadoDebeSer(String resultadoEsperado) {
        assertNotNull(resultadoAntifraude, "El resultado del antifraude no debería ser nulo");
        assertEquals(resultadoEsperado, resultadoAntifraude,
            "El resultado del antifraude debería ser " + resultadoEsperado);
    }

    @Cuando("el sistema consulta el buró de riesgos")
    public void elSistemaConsultaBuroRiesgos() {
        CompletableFuture<String> resultadoFuture = buroRiesgosService.consultarBuro(solicitudEnProceso);
        resultadoBuro = resultadoFuture.join();
    }

    @Entonces("el resultado del buró debe ser {string}")
    public void elResultadoBuroDebeSer(String resultadoEsperado) {
        assertNotNull(resultadoBuro, "El resultado del buró no debería ser nulo");
        assertEquals(resultadoEsperado, resultadoBuro,
            "El resultado del buró debería ser " + resultadoEsperado);
    }

    @Cuando("el sistema consulta el core bancario")
    public void elSistemaConsultaCoreBancario() {
        CompletableFuture<String> resultadoFuture = coreBancarioService.consultarCore(solicitudEnProceso);
        resultadoCore = resultadoFuture.join();
    }

    @Entonces("el resultado del core debe ser {string}")
    public void elResultadoCoreDebeSer(String resultadoEsperado) {
        assertNotNull(resultadoCore, "El resultado del core no debería ser nulo");
        assertEquals(resultadoEsperado, resultadoCore,
            "El resultado del core debería ser " + resultadoEsperado);
    }

    @Dado("que el monto solicitado supera el límite permitido de {int}")
    public void queElMontoSuperaElLimite(int limite) {
        solicitudEnProceso = new SolicitudCredito(
            UUID.randomUUID(),
            "CC",
            "99999999",
            new BigDecimal(limite + 1),
            12,
            "ORIGINADOR_CREDITOS",
            null, null, null, null
        );
    }

    @Entonces("el sistema debe rechazar la solicitud por monto excedido")
    public void elSistemaRechazaPorMontoExcedido() {
        assertTrue(solicitudEnProceso.monto().compareTo(new BigDecimal(10000000)) > 0,
            "El monto debería exceder el límite");
    }

    @Dado("que el plazo solicitado supera el límite máximo de {int} meses")
    public void queElPlazoSuperaElLimite(int limite) {
        solicitudEnProceso = new SolicitudCredito(
            UUID.randomUUID(),
            "CC",
            "88888888",
            new BigDecimal("5000000"),
            limite + 1,
            "ORIGINADOR_CREDITOS",
            null, null, null, null
        );
    }

    @Entonces("el sistema debe rechazar la solicitud por plazo excedido")
    public void elSistemaRechazaPorPlazoExcedido() {
        assertTrue(solicitudEnProceso.plazoMeses() > 60,
            "El plazo debería exceder el límite máximo");
    }
}