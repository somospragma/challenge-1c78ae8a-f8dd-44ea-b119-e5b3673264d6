package com.credito.gestion.infrastructure.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "solicitudes_credito", indexes = {
    @Index(name = "idx_numero_operacion", columnList = "numero_operacion"),
    @Index(name = "idx_documento", columnList = "tipo_documento, numero_documento")
})
public class SolicitudCreditoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "numero_operacion", nullable = false, unique = true)
    private UUID numeroOperacion;

    @Column(name = "tipo_documento", nullable = false, length = 10)
    private String tipoDocumento;

    @Column(name = "numero_documento", nullable = false, length = 30)
    private String numeroDocumento;

    @Column(name = "monto", nullable = false, precision = 19, scale = 4)
    private BigDecimal monto;

    @Column(name = "plazo", nullable = false)
    private Integer plazo;

    @Column(name = "destino", nullable = false, length = 100)
    private String destino;

    @Column(name = "resultado_antifraude", length = 50)
    private String resultadoAntifraude;

    @Column(name = "resultado_buro", length = 50)
    private String resultadoBuro;

    @Column(name = "resultado_core", length = 50)
    private String resultadoCore;

    @Column(name = "observaciones", length = 500)
    private String observaciones;

    @Column(name = "estado", nullable = false, length = 30)
    private String estado;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getNumeroOperacion() {
        return numeroOperacion;
    }

    public void setNumeroOperacion(UUID numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Integer getPlazo() {
        return plazo;
    }

    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getResultadoAntifraude() {
        return resultadoAntifraude;
    }

    public void setResultadoAntifraude(String resultadoAntifraude) {
        this.resultadoAntifraude = resultadoAntifraude;
    }

    public String getResultadoBuro() {
        return resultadoBuro;
    }

    public void setResultadoBuro(String resultadoBuro) {
        this.resultadoBuro = resultadoBuro;
    }

    public String getResultadoCore() {
        return resultadoCore;
    }

    public void setResultadoCore(String resultadoCore) {
        this.resultadoCore = resultadoCore;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}