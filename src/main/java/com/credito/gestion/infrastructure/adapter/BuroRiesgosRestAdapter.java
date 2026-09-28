package com.credito.gestion.infrastructure.adapter;

import com.credito.gestion.domain.model.SolicitudCredito;
import com.credito.gestion.domain.port.BuroRiesgosService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class BuroRiesgosRestAdapter implements BuroRiesgosService {

    private static final Logger log = LoggerFactory.getLogger(BuroRiesgosRestAdapter.class);
    private static final String BURO_URL = "http://localhost:8082/api/buro/consultar";
    private static final int TIMEOUT_SECONDS = 2;

    private final RestTemplate restTemplate;

    public BuroRiesgosRestAdapter(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Retry(name = "buro")
    @CircuitBreaker(name = "buro", fallbackMethod = "consultarBuroFallback")
    public CompletableFuture<String> consultarBuro(SolicitudCredito solicitud) {
        try {
            log.info("Consultando buró de riesgos para operacion: {}", solicitud.numeroOperacion());
            
            Map<String, Object> payload = new HashMap<>();
            payload.put("numeroOperacion", solicitud.numeroOperacion().toString());
            payload.put("tipoDocumento", solicitud.tipoDocumento());
            payload.put("numeroDocumento", solicitud.numeroDocumento());
            payload.put("monto", solicitud.monto().toString());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

            Map<String, Object> response = restTemplate.postForObject(
                    BURO_URL, request, Map.class);

            String resultado = (String) response.get("resultado");
            String calificacion = (String) response.get("calificacion");
            
            log.info("Resultado buró para operacion {}: resultado={}, calificacion={}", 
                    solicitud.numeroOperacion(), resultado, calificacion);
            
            String resultadoFinal = resultado != null ? resultado : "SIN_INFORMACION";
            return CompletableFuture.completedFuture(resultadoFinal);
            
        } catch (Exception e) {
            log.error("Error en consulta buró para operacion {}: {}", 
                    solicitud.numeroOperacion(), e.getMessage());
            return CompletableFuture.completedFuture("ERROR_CONSULTA");
        }
    }

    private CompletableFuture<String> consultarBuroFallback(SolicitudCredito solicitud, Throwable t) {
        log.warn("Fallback buró activado para operacion {}: {}", 
                solicitud.numeroOperacion(), t.getMessage());
        return CompletableFuture.completedFuture("SIN_RESPUESTA");
    }
}