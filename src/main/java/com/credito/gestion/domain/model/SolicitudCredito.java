package com.credito.gestion.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record SolicitudCredito(
    UUID numeroOperacion,
    String tipoDocumento,
    String numeroDocumento,
    String nombreCompleto,
    BigDecimal montoSolicitado,
    Integer plazoMeses,
    String estado,
    LocalDate fechaSolicitud,
    LocalDate fechaRespuesta,
    String resultadoAntifraude,
    String resultadoBuro,
    String resultadoCore,
    String observaciones
) {
    public SolicitudCredito {
        if (numeroOperacion == null) {
            throw new IllegalArgumentException("El número de operación no puede ser nulo");
        }
        if (tipoDocumento == null || tipoDocumento.isBlank()) {
            throw new IllegalArgumentException("El tipo de documento no puede ser nulo o vacío");
        }
        if (numeroDocumento == null || numeroDocumento.isBlank()) {
            throw new IllegalArgumentException("El número de documento no puede ser nulo o vacío");
        }
        if (nombreCompleto == null || nombreCompleto.isBlank()) {
            throw new IllegalArgumentException("El nombre completo no puede ser nulo o vacío");
        }
        if (montoSolicitado == null || montoSolicitado.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto solicitado debe ser mayor que cero");
        }
        if (plazoMeses == null || plazoMeses <= 0) {
            throw new IllegalArgumentException("El plazo en meses debe ser mayor que cero");
        }
        if (estado == null || estado.isBlank()) {
            estado = "PENDIENTE";
        }
        if (fechaSolicitud == null) {
            fechaSolicitud = LocalDate.now();
        }
    }

    public SolicitudCredito conResultadoAntifraude(String resultado) {
        return new SolicitudCredito(
            this.numeroOperacion,
            this.tipoDocumento,
            this.numeroDocumento,
            this.nombreCompleto,
            this.montoSolicitado,
            this.plazoMeses,
            this.estado,
            this.fechaSolicitud,
            this.fechaRespuesta,
            resultado,
            this.resultadoBuro,
            this.resultadoCore,
            this.observaciones
        );
    }

    public SolicitudCredito conResultadoBuro(String resultado) {
        return new SolicitudCredito(
            this.numeroOperacion,
            this.tipoDocumento,
            this.numeroDocumento,
            this.nombreCompleto,
            this.montoSolicitado,
            this.plazoMeses,
            this.estado,
            this.fechaSolicitud,
            this.fechaRespuesta,
            this.resultadoAntifraude,
            resultado,
            this.resultadoCore,
            this.observaciones
        );
    }

    public SolicitudCredito conResultadoCore(String resultado) {
        return new SolicitudCredito(
            this.numeroOperacion,
            this.tipoDocumento,
            this.numeroDocumento,
            this.nombreCompleto,
            this.montoSolicitado,
            this.plazoMeses,
            "APROBADA".equals(resultado) ? "APROBADA" : "RECHAZADA",
            this.fechaSolicitud,
            LocalDate.now(),
            this.resultadoAntifraude,
            this.resultadoBuro,
            resultado,
            this.observaciones
        );
    }

    public SolicitudCredito conObservaciones(String observaciones) {
        return new SolicitudCredito(
            this.numeroOperacion,
            this.tipoDocumento,
            this.numeroDocumento,
            this.nombreCompleto,
            this.montoSolicitado,
            this.plazoMeses,
            this.estado,
            this.fechaSolicitud,
            this.fechaRespuesta,
            this.resultadoAntifraude,
            this.resultadoBuro,
            this.resultadoCore,
            observaciones
        );
    }
}