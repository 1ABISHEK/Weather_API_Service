package com.example.weatherapi.exception;

public class ProviderException extends RuntimeException {
    public ProviderException(String message, Throwable cause) {
        super(message, cause);
    }
}
