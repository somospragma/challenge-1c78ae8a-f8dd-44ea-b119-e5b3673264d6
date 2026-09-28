package com.credito.gestion.domain.port;

import com.credito.gestion.domain.model.SolicitudCredito;
import java.util.concurrent.CompletableFuture;

/**
 * Puerto de salida para interactuar con el buró de riesgos.
 * Define la abstracción que el dominio usa para consultar información crediticia.
 * La implementación concreta reside en la capa de infraestructura.
 */
public interface BuroRiesgosService {

    /**
     * Consulta el buró de riesgos para obtener el historial crediticio del cliente.
     * Retorna el resultado de la consulta: APROBADO, RECHAZADO o REVISION.
     *
     * @param solicitud la solicitud de crédito con los datos del cliente
     * @return CompletableFuture con el resultado de la consulta al buró
     */
    CompletableFuture<String> consultarBuro(SolicitudCredito solicitud);

    /**
     * Verifica si el cliente tiene alertas activas en el buró de riesgos.
     *
     * @param tipoDocumento tipo de documento del cliente
     * @param numeroDocumento número de documento del cliente
     * @return CompletableFuture con true si hay alertas, false en caso contrario
     */
    CompletableFuture<Boolean> verificarAlertas(String tipoDocumento, String numeroDocumento);

    /**
     * Obtiene el score crediticio del cliente del buró.
     *
     * @param tipoDocumento tipo de documento del cliente
     * @param numeroDocumento número de documento del cliente
     * @return CompletableFuture con el score crediticio (0-1000)
     */
    CompletableFuture<Integer> obtenerScore(String tipoDocumento, String numeroDocumento);
}