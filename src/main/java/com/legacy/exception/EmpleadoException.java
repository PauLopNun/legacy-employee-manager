package com.legacy.exception;

public class EmpleadoException extends RuntimeException {
    public EmpleadoException(String message) {
        super(message);
    }

    public EmpleadoException(String message, Throwable cause) {
        super(message, cause);
    }
}