package com.credito.gestion.domain.port;

import com.credito.gestion.domain.model.SolicitudCredito;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Puerto de salida para interactuar con el core bancario.
 * Define la abstracción que el dominio usa para registrar y confirmar
 * las operaciones de crédito en el sistema central de la institución.
 */
public interface CoreBancarioService {

    /**
     * Registra la operación de crédito en el core bancario.
     *
     * @param numeroOperacion identificador único de la operación
     * @param monto monto aprobado del crédito
     * @param solicitud datos completos de la solicitud
     * @return CompletableFuture con el resultado del registro
     */
    CompletableFuture<String> registrarOperacion(UUID numeroOperacion, BigDecimal monto, SolicitudCredito solicitud);

    /**
     * Confirma la operación de crédito en el core bancario.
     * Se invoca después de que todos los servicios de validación aprobaran.
     *
     * @param numeroOperacion identificador único de la operación
     * @return CompletableFuture con la confirmación del core
     */
    CompletableFuture<String> confirmarOperacion(UUID numeroOperacion);

    /**
     * Consulta el estado de una operación previamente registrada.
     *
     * @param numeroOperacion identificador único de la operación
     * @return CompletableFuture con el estado actual de la operación
     */
    CompletableFuture<String> consultarEstado(UUID numeroOperacion);

    /**
     * Cancela una operación de crédito en el core bancario.
     * Se invoca cuando falla alguna validación posterior al registro.
     *
     * @param numeroOperacion identificador único de la operación
     * @param motivo motivo de la cancelación
     * @return CompletableFuture con el resultado de la cancelación
     */
    CompletableFuture<String> cancelarOperacion(UUID numeroOperacion, String motivo);
}