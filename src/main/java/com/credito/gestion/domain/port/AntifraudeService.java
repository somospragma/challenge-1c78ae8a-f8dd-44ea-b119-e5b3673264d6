package com.credito.gestion.domain.port;

import com.credito.gestion.domain.model.SolicitudCredito;
import java.util.concurrent.CompletableFuture;

public interface AntifraudeService {
    CompletableFuture<String> evaluarRiesgo(SolicitudCredito solicitud);
}