package com.example.weatherapi.config;

import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RateLimiteConfig {

    @Bean
    public io.github.resilience4j.ratelimiter.RateLimiter weatherApiRateLimiter() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(500))
                .limitRefreshPeriod(Duration.ofSeconds(60)) // refresh every 60s
                .limitForPeriod(100) // max 100 requests per period
                .build();

        return RateLimiterRegistry.of(config).rateLimiter("weatherApiLimiter");
    }
}
