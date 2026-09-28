package com.credito.gestion.infrastructure.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SolicitudDuplicadaException.class)
    public ResponseEntity<ErrorResponse> handleSolicitudDuplicada(
            SolicitudDuplicadaException ex, WebRequest request) {
        logger.warn("Solicitud duplicada detectada: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Conflicto - Solicitud Duplicada")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .numeroOperacion(ex.getNumeroOperacion())
                .build();
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<ErrorResponse> handleTimeout(
            TimeoutException ex, WebRequest request) {
        logger.error("Timeout en servicio externo: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.GATEWAY_TIMEOUT.value())
                .error("Gateway Timeout")
                .message("El servicio externo no respondió a tiempo: " + ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .servicio(ex.getServicio())
                .build();
        
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(errorResponse);
    }

    @ExceptionHandler(CoreBancarioException.class)
    public ResponseEntity<ErrorResponse> handleCoreBancarioException(
            CoreBancarioException ex, WebRequest request) {
        logger.error("Error del core bancario: {}", ex.getMessage());
        
        HttpStatus status = ex.getStatusCode() != null ? 
                HttpStatus.valueOf(ex.getStatusCode()) : HttpStatus.BAD_GATEWAY;
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error("Error en Core Bancario")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .servicio("core-bancario")
                .codigoError(ex.getCodigoError())
                .build();
        
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        logger.warn("Error de validación: {}", ex.getMessage());
        
        Map<String, String> errores = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null ? 
                                error.getDefaultMessage() : "Valor inválido",
                        (existing, replacement) -> existing
                ));
        
        ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Error de Validación")
                .message("Los datos proporcionados no son válidos")
                .path(request.getDescription(false).replace("uri=", ""))
                .errores(errores)
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {
        logger.warn("Argumento ilegal: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Solicitud Inválida")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        logger.error("Error interno del servidor: ", ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Error Interno del Servidor")
                .message("Ha ocurrido un error inesperado. Por favor, contacte al administrador.")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    public static class ErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String path;
        private String numeroOperacion;
        private String servicio;
        private String codigoError;

        public static ErrorResponseBuilder builder() {
            return new ErrorResponseBuilder();
        }

        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
        public String getNumeroOperacion() { return numeroOperacion; }
        public void setNumeroOperacion(String numeroOperacion) { this.numeroOperacion = numeroOperacion; }
        public String getServicio() { return servicio; }
        public void setServicio(String servicio) { this.servicio = servicio; }
        public String getCodigoError() { return codigoError; }
        public void setCodigoError(String codigoError) { this.codigoError = codigoError; }

        public static class ErrorResponseBuilder {
            private LocalDateTime timestamp;
            private int status;
            private String error;
            private String message;
            private String path;
            private String numeroOperacion;
            private String servicio;
            private String codigoError;

            public ErrorResponseBuilder timestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp; return this; }
            public ErrorResponseBuilder status(int status) {
                this.status = status; return this; }
            public ErrorResponseBuilder error(String error) {
                this.error = error; return this; }
            public ErrorResponseBuilder message(String message) {
                this.message = message; return this; }
            public ErrorResponseBuilder path(String path) {
                this.path = path; return this; }
            public ErrorResponseBuilder numeroOperacion(String numeroOperacion) {
                this.numeroOperacion = numeroOperacion; return this; }
            public ErrorResponseBuilder servicio(String servicio) {
                this.servicio = servicio; return this; }
            public ErrorResponseBuilder codigoError(String codigoError) {
                this.codigoError = codigoError; return this; }
            public ErrorResponse build() {
                ErrorResponse response = new ErrorResponse();
                response.timestamp = this.timestamp;
                response.status = this.status;
                response.error = this.error;
                response.message = this.message;
                response.path = this.path;
                response.numeroOperacion = this.numeroOperacion;
                response.servicio = this.servicio;
                response.codigoError = this.codigoError;
                return response;
            }
        }
    }

    public static class ValidationErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String path;
        private Map<String, String> errores;

        public static ValidationErrorResponseBuilder builder() {
            return new ValidationErrorResponseBuilder();
        }

        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
        public Map<String, String> getErrores() { return errores; }
        public void setErrores(Map<String, String> errores) { this.errores = errores; }

        public static class ValidationErrorResponseBuilder {
            private LocalDateTime timestamp;
            private int status;
            private String error;
            private String message;
            private String path;
            private Map<String, String> errores;

            public ValidationErrorResponseBuilder timestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp; return this; }
            public ValidationErrorResponseBuilder status(int status) {
                this.status = status; return this; }
            public ValidationErrorResponseBuilder error(String error) {
                this.error = error; return this; }
            public ValidationErrorResponseBuilder message(String message) {
                this.message = message; return this; }
            public ValidationErrorResponseBuilder path(String path) {
                this.path = path; return this; }
            public ValidationErrorResponseBuilder errores(Map<String, String> errores) {
                this.errores = errores; return this; }
            public ValidationErrorResponse build() {
                ValidationErrorResponse response = new ValidationErrorResponse();
                response.timestamp = this.timestamp;
                response.status = this.status;
                response.error = this.error;
                response.message = this.message;
                response.path = this.path;
                response.errores = this.errores;
                return response;
            }
        }
    }
}