package com.credito.gestion.infrastructure.exception;

public class SolicitudDuplicadaException extends RuntimeException {

    private final String numeroOperacion;
    private final String tipoDocumento;
    private final String numeroDocumento;

    public SolicitudDuplicadaException(String numeroOperacion) {
        super(String.format("Ya existe una solicitud con el número de operación: %s", numeroOperacion));
        this.numeroOperacion = numeroOperacion;
        this.tipoDocumento = null;
        this.numeroDocumento = null;
    }

    public SolicitudDuplicadaException(String tipoDocumento, String numeroDocumento) {
        super(String.format("Ya existe una solicitud aprobada para el documento: %s %s", 
                tipoDocumento, numeroDocumento));
        this.numeroOperacion = null;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
    }

    public SolicitudDuplicadaException(String numeroOperacion, String tipoDocumento, String numeroDocumento) {
        super(String.format("Solicitud duplicada - Número de operación: %s, Documento: %s %s", 
                numeroOperacion, tipoDocumento, numeroDocumento));
        this.numeroOperacion = numeroOperacion;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroOperacion() {
        return numeroOperacion;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    @Override
    public String toString() {
        return "SolicitudDuplicadaException{" +
                "numeroOperacion='" + numeroOperacion + '\'' +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                "} " + super.toString();
    }
}