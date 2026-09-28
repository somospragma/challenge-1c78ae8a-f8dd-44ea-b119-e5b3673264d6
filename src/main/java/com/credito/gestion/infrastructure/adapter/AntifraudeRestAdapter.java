package com.credito.gestion.infrastructure.adapter;

import com.credito.gestion.domain.model.SolicitudCredito;
import com.credito.gestion.domain.port.AntifraudeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class AntifraudeRestAdapter implements AntifraudeService {

    private static final Logger log = LoggerFactory.getLogger(AntifraudeRestAdapter.class);
    private static final String ANTIFRAUDE_URL = "http://localhost:8081/api/antifraude/evaluar";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public AntifraudeRestAdapter(@Qualifier("restTemplate") RestTemplate restTemplate,
                                  ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    @Async("taskExecutor")
    @CircuitBreaker(name = "antifraude", fallbackMethod = "evaluarRiesgoFallback")
    public CompletableFuture<String> evaluarRiesgo(SolicitudCredito solicitud) {
        try {
            log.info("Evaluando riesgo antifraude para operacion: {}", solicitud.numeroOperacion());
            
            Map<String, Object> payload = new HashMap<>();
            payload.put("numeroOperacion", solicitud.numeroOperacion().toString());
            payload.put("tipoDocumento", solicitud.tipoDocumento());
            payload.put("numeroDocumento", solicitud.numeroDocumento());
            payload.put("monto", solicitud.monto().toString());
            payload.put("plazo", solicitud.plazo());
            payload.put("destino", solicitud.destino());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

            Map<String, Object> response = restTemplate.postForObject(
                    ANTIFRAUDE_URL, request, Map.class);

            String resultado = (String) response.get("resultado");
            log.info("Resultado antifraude para operacion {}: {}", solicitud.numeroOperacion(), resultado);
            
            return CompletableFuture.completedFuture(resultado != null ? resultado : "APROBADO");
            
        } catch (Exception e) {
            log.error("Error en evaluacion antifraude para operacion {}: {}", 
                    solicitud.numeroOperacion(), e.getMessage());
            return CompletableFuture.completedFuture("ERROR");
        }
    }

    private CompletableFuture<String> evaluarRiesgoFallback(SolicitudCredito solicitud, Throwable t) {
        log.warn("Fallback antifraude activado para operacion {}: {}", 
                solicitud.numeroOperacion(), t.getMessage());
        return CompletableFuture.completedFuture("PENDIENTE_REVISION");
    }
}