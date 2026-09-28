package com.credito.gestion.infrastructure.adapter;

import com.credito.gestion.domain.model.SolicitudCredito;
import com.credito.gestion.domain.port.CoreBancarioService;
import com.credito.gestion.infrastructure.exception.CoreBancarioException;
import com.credito.gestion.infrastructure.exception.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class CoreBancarioRestAdapter implements CoreBancarioService {

    private static final Logger log = LoggerFactory.getLogger(CoreBancarioRestAdapter.class);
    private static final String CORE_BANCARIO_URL = "http://localhost:8081/api/core";

    private final RestTemplate restTemplate;
    private final io.github.resilience4j.circuitbreaker.CircuitBreaker circuitBreaker;

    public CoreBancarioRestAdapter(
            @Qualifier("coreRestTemplate") RestTemplate restTemplate,
            @Qualifier("coreCircuitBreaker") io.github.resilience4j.circuitbreaker.CircuitBreaker circuitBreaker) {
        this.restTemplate = restTemplate;
        this.circuitBreaker = circuitBreaker;
    }

    @Override
    @CircuitBreaker(name = "coreBancario", fallbackMethod = "registrarEnCoreFallback")
    public CompletableFuture<String> registrarSolicitud(SolicitudCredito solicitud) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set("X-Idempotency-Key", solicitud.numeroOperacion().toString());

                Map<String, Object> payload = new HashMap<>();
                payload.put("numeroOperacion", solicitud.numeroOperacion().toString());
                payload.put("tipoDocumento", solicitud.tipoDocumento());
                payload.put("numeroDocumento", solicitud.numeroDocumento());
                payload.put("monto", solicitud.monto().toString());
                payload.put("plazo", solicitud.plazo().toString());
                payload.put("tipoCredito", solicitud.tipoCredito());
                payload.put("tasaInteres", solicitud.tasaInteres().toString());

                HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

                ResponseEntity<String> response = restTemplate.exchange(
                        CORE_BANCARIO_URL + "/solicitudes",
                        HttpMethod.POST,
                        request,
                        String.class
                );

                if (response.getStatusCode().is2xxSuccessful()) {
                    log.info("Solicitud {} registrada exitosamente en core bancario", solicitud.numeroOperacion());
                    return response.getBody() != null ? response.getBody() : "APROBADA";
                } else {
                    throw new CoreBancarioException("Respuesta no exitosa del core bancario: " + response.getStatusCode());
                }

            } catch (HttpServerErrorException e) {
                log.error("Error 5xx del core bancario para solicitud {}: {}", solicitud.numeroOperacion(), e.getMessage());
                throw new CoreBancarioException("Error del core bancario: " + e.getStatusCode());
            } catch (HttpClientErrorException e) {
                log.error("Error 4xx del core bancario para solicitud {}: {}", solicitud.numeroOperacion(), e.getMessage());
                throw new CoreBancarioException("Error de cliente del core bancario: " + e.getStatusCode());
            } catch (org.springframework.web.client.ResourceAccessException e) {
                log.error("Timeout conectando con core bancario para solicitud {}", solicitud.numeroOperacion());
                throw new TimeoutException("Timeout al conectar con el core bancario");
            }
        });
    }

    private CompletableFuture<String> registrarEnCoreFallback(SolicitudCredito solicitud, Throwable t) {
        log.warn("Fallback ejecutado para solicitud {} debido a: {}", solicitud.numeroOperacion(), t.getMessage());
        if (t instanceof TimeoutException) {
            throw (TimeoutException) t;
        }
        if (t instanceof CoreBancarioException) {
            throw (CoreBancarioException) t;
        }
        throw new CoreBancarioException("Servicio de core bancario no disponible temporalmente");
    }
}