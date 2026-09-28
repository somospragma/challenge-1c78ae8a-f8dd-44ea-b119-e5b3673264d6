package com.credito.gestion.infrastructure.adapter;

import com.credito.gestion.domain.model.SolicitudCredito;
import com.credito.gestion.domain.port.SolicitudCreditoRepository;
import com.credito.gestion.infrastructure.entity.SolicitudCreditoEntity;
import com.credito.gestion.infrastructure.repository.SolicitudCreditoJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class SolicitudCreditoJpaAdapter implements SolicitudCreditoRepository {

    private final SolicitudCreditoJpaRepository jpaRepository;
    private final SolicitudCreditoMapper mapper;

    public SolicitudCreditoJpaAdapter(SolicitudCreditoJpaRepository jpaRepository,
                                       SolicitudCreditoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public SolicitudCredito save(SolicitudCredito solicitud) {
        SolicitudCreditoEntity entidad = mapper.toEntity(solicitud);
        SolicitudCreditoEntity guardada = jpaRepository.save(entidad);
        return mapper.toDomain(guardada);
    }

    @Override
    public Optional<SolicitudCredito> findByNumeroOperacion(UUID numeroOperacion) {
        return jpaRepository.findByNumeroOperacion(numeroOperacion)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<SolicitudCredito> findByTipoDocumentoAndNumeroDocumento(
            String tipoDocumento, String numeroDocumento) {
        return jpaRepository.findByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento)
                .map(mapper::toDomain);
    }
}