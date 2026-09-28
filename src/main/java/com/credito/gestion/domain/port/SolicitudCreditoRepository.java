package com.credito.gestion.domain.port;

import com.credito.gestion.domain.model.SolicitudCredito;
import java.util.Optional;
import java.util.UUID;

public interface SolicitudCreditoRepository {
    SolicitudCredito save(SolicitudCredito solicitud);
    
    Optional<SolicitudCredito> findByNumeroOperacion(UUID numeroOperacion);
    
    Optional<SolicitudCredito> findByTipoDocumentoAndNumeroDocumento(String tipoDocumento, String numeroDocumento);
}