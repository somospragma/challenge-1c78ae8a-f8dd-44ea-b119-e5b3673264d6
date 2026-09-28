package com.credito.gestion.infrastructure.exception;

public class CoreBancarioException extends RuntimeException {

    private final String codigoError;
    private final int codigoHttp;
    private final String numeroOperacion;
    private final boolean esReintentable;

    public CoreBancarioException(String mensaje) {
        super(mensaje);
        this.codigoError = null;
        this.codigoHttp = 500;
        this.numeroOperacion = null;
        this.esReintentable = false;
    }

    public CoreBancarioException(String mensaje, String codigoError) {
        super(mensaje);
        this.codigoError = codigoError;
        this.codigoHttp = 500;
        this.numeroOperacion = null;
        this.esReintentable = false;
    }

    public CoreBancarioException(String mensaje, String codigoError, int codigoHttp) {
        super(mensaje);
        this.codigoError = codigoError;
        this.codigoHttp = codigoHttp;
        this.numeroOperacion = null;
        this.esReintentable = esError5xx(codigoHttp);
    }

    public CoreBancarioException(String mensaje, String codigoError, int codigoHttp, String numeroOperacion) {
        super(mensaje);
        this.codigoError = codigoError;
        this.codigoHttp = codigoHttp;
        this.numeroOperacion = numeroOperacion;
        this.esReintentable = esError5xx(codigoHttp);
    }

    public CoreBancarioException(String mensaje, String codigoError, int codigoHttp, String numeroOperacion, boolean esReintentable) {
        super(mensaje);
        this.codigoError = codigoError;
        this.codigoHttp = codigoHttp;
        this.numeroOperacion = numeroOperacion;
        this.esReintentable = esReintentable;
    }

    public CoreBancarioException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = null;
        this.codigoHttp = 500;
        this.numeroOperacion = null;
        this.esReintentable = false;
    }

    public CoreBancarioException(String mensaje, String codigoError, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
        this.codigoHttp = 500;
        this.numeroOperacion = null;
        this.esReintentable = false;
    }

    public CoreBancarioException(String mensaje, String codigoError, int codigoHttp, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
        this.codigoHttp = codigoHttp;
        this.numeroOperacion = null;
        this.esReintentable = esError5xx(codigoHttp);
    }

    public CoreBancarioException(String mensaje, String codigoError, int codigoHttp, String numeroOperacion, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
        this.codigoHttp = codigoHttp;
        this.numeroOperacion = numeroOperacion;
        this.esReintentable = esError5xx(codigoHttp);
    }

    public CoreBancarioException(String mensaje, String codigoError, int codigoHttp, String numeroOperacion, boolean esReintentable, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
        this.codigoHttp = codigoHttp;
        this.numeroOperacion = numeroOperacion;
        this.esReintentable = esReintentable;
    }

    private static boolean esError5xx(int codigoHttp) {
        return codigoHttp >= 500 && codigoHttp < 600;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public int getCodigoHttp() {
        return codigoHttp;
    }

    public String getNumeroOperacion() {
        return numeroOperacion;
    }

    public boolean isEsReintentable() {
        return esReintentable;
    }

    public boolean tieneNumeroOperacion() {
        return numeroOperacion != null && !numeroOperacion.isBlank();
    }

    public boolean tieneCodigoError() {
        return codigoError != null && !codigoError.isBlank();
    }

    public boolean esErrorServidor() {
        return esError5xx(codigoHttp);
    }

    public boolean esErrorCliente() {
        return codigoHttp >= 400 && codigoHttp < 500;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CoreBancarioException: ");
        sb.append(getMessage());
        if (tieneCodigoError()) {
            sb.append(" | CodigoError: ").append(codigoError);
        }
        sb.append(" | CodigoHTTP: ").append(codigoHttp);
        if (tieneNumeroOperacion()) {
            sb.append(" | NumeroOperacion: ").append(numeroOperacion);
        }
        sb.append(" | Reintentable: ").append(esReintentable);
        return sb.toString();
    }
}