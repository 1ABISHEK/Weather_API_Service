package com.example.weatherapi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingUtils {
    private static final Logger log = LoggerFactory.getLogger(LoggingUtils.class);

    public static void logRequest(String endpoint, String params) {
        log.info("Incoming request -> [{}] Params: {}", endpoint, params);
    }

    public static void logResponse(String endpoint, Object response) {
        log.info("Response from [{}] -> {}", endpoint, response);
    }

    public static void logError(String endpoint, String error) {
        log.error("Error at [{}] -> {}", endpoint, error);
    }
}
