package com.credito.gestion.infrastructure.exception;

public class TimeoutException extends RuntimeException {

    private final String servicio;
    private final String numeroOperacion;
    private final long tiempoEspera;

    public TimeoutException(String mensaje) {
        super(mensaje);
        this.servicio = null;
        this.numeroOperacion = null;
        this.tiempoEspera = 0L;
    }

    public TimeoutException(String mensaje, String servicio) {
        super(mensaje);
        this.servicio = servicio;
        this.numeroOperacion = null;
        this.tiempoEspera = 0L;
    }

    public TimeoutException(String mensaje, String servicio, String numeroOperacion) {
        super(mensaje);
        this.servicio = servicio;
        this.numeroOperacion = numeroOperacion;
        this.tiempoEspera = 0L;
    }

    public TimeoutException(String mensaje, String servicio, String numeroOperacion, long tiempoEspera) {
        super(mensaje);
        this.servicio = servicio;
        this.numeroOperacion = numeroOperacion;
        this.tiempoEspera = tiempoEspera;
    }

    public TimeoutException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.servicio = null;
        this.numeroOperacion = null;
        this.tiempoEspera = 0L;
    }

    public TimeoutException(String mensaje, String servicio, Throwable causa) {
        super(mensaje, causa);
        this.servicio = servicio;
        this.numeroOperacion = null;
        this.tiempoEspera = 0L;
    }

    public TimeoutException(String mensaje, String servicio, String numeroOperacion, Throwable causa) {
        super(mensaje, causa);
        this.servicio = servicio;
        this.numeroOperacion = numeroOperacion;
        this.tiempoEspera = 0L;
    }

    public TimeoutException(String mensaje, String servicio, String numeroOperacion, long tiempoEspera, Throwable causa) {
        super(mensaje, causa);
        this.servicio = servicio;
        this.numeroOperacion = numeroOperacion;
        this.tiempoEspera = tiempoEspera;
    }

    public String getServicio() {
        return servicio;
    }

    public String getNumeroOperacion() {
        return numeroOperacion;
    }

    public long getTiempoEspera() {
        return tiempoEspera;
    }

    public boolean tieneNumeroOperacion() {
        return numeroOperacion != null && !numeroOperacion.isBlank();
    }

    public boolean tieneServicio() {
        return servicio != null && !servicio.isBlank();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TimeoutException: ");
        sb.append(getMessage());
        if (tieneServicio()) {
            sb.append(" | Servicio: ").append(servicio);
        }
        if (tieneNumeroOperacion()) {
            sb.append(" | NumeroOperacion: ").append(numeroOperacion);
        }
        if (tiempoEspera > 0) {
            sb.append(" | TiempoEspera: ").append(tiempoEspera).append("ms");
        }
        return sb.toString();
    }
}