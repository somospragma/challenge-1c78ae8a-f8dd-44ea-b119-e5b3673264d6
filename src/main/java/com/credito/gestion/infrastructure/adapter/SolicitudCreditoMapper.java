package com.credito.gestion.infrastructure.adapter;

import com.credito.gestion.domain.model.SolicitudCredito;
import com.credito.gestion.infrastructure.entity.SolicitudCreditoEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class SolicitudCreditoMapper {

    public SolicitudCredito toDomain(SolicitudCreditoEntity entity) {
        return new SolicitudCredito(
                entity.getNumeroOperacion(),
                entity.getTipoDocumento(),
                entity.getNumeroDocumento(),
                entity.getMonto(),
                entity.getPlazo(),
                entity.getDestino(),
                entity.getResultadoAntifraude(),
                entity.getResultadoBuro(),
                entity.getResultadoCore(),
                entity.getObservaciones(),
                entity.getEstado(),
                entity.getFechaCreacion(),
                entity.getFechaActualizacion()
        );
    }

    public SolicitudCreditoEntity toDomain(SolicitudCredito solicitud) {
        SolicitudCreditoEntity entity = new SolicitudCreditoEntity();
        entity.setNumeroOperacion(solicitud.numeroOperacion());
        entity.setTipoDocumento(solicitud.tipoDocumento());
        entity.setNumeroDocumento(solicitud.numeroDocumento());
        entity.setMonto(solicitud.monto());
        entity.setPlazo(solicitud.plazo());
        entity.setDestino(solicitud.destino());
        entity.setResultadoAntifraude(solicitud.resultadoAntifraude());
        entity.setResultadoBuro(solicitud.resultadoBuro());
        entity.setResultadoCore(solicitud.resultadoCore());
        entity.setObservaciones(solicitud.observaciones());
        entity.setEstado(solicitud.estado());
        if (solicitud.fechaCreacion() != null) {
            entity.setFechaCreacion(solicitud.fechaCreacion());
        } else {
            entity.setFechaCreacion(LocalDateTime.now());
        }
        entity.setFechaActualizacion(LocalDateTime.now());
        return entity;
    }

    public SolicitudCreditoEntity toEntity(SolicitudCredito solicitud) {
        SolicitudCreditoEntity entity = new SolicitudCreditoEntity();
        if (solicitud.numeroOperacion() != null) {
            entity.setNumeroOperacion(solicitud.numeroOperacion());
        } else {
            entity.setNumeroOperacion(UUID.randomUUID());
        }
        entity.setTipoDocumento(solicitud.tipoDocumento());
        entity.setNumeroDocumento(solicitud.numeroDocumento());
        entity.setMonto(solicitud.monto());
        entity.setPlazo(solicitud.plazo());
        entity.setDestino(solicitud.destino());
        entity.setResultadoAntifraude(solicitud.resultadoAntifraude());
        entity.setResultadoBuro(solicitud.resultadoBuro());
        entity.setResultadoCore(solicitud.resultadoCore());
        entity.setObservaciones(solicitud.observaciones());
        entity.setEstado(solicitud.estado() != null ? solicitud.estado() : "PENDIENTE");
        LocalDateTime ahora = LocalDateTime.now();
        entity.setFechaCreacion(ahora);
        entity.setFechaActualizacion(ahora);
        return entity;
    }
}