package com.credito.gestion.infrastructure.repository;

import com.credito.gestion.infrastructure.entity.SolicitudCreditoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SolicitudCreditoJpaRepository extends JpaRepository<SolicitudCreditoEntity, UUID> {

    Optional<SolicitudCreditoEntity> findByNumeroOperacion(UUID numeroOperacion);

    Optional<SolicitudCreditoEntity> findByTipoDocumentoAndNumeroDocumento(
            String tipoDocumento, String numeroDocumento);
}