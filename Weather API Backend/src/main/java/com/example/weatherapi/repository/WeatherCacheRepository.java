package com.example.weatherapi.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import com.example.weatherapi.model.dto.WeatherDto;

@Repository
public class WeatherCacheRepository {

    @Cacheable(value = "weather", key = "#city")
    public WeatherDto getCachedWeather(String city) {
        // This will be populated by Spring caching automatically
        return null;
    }
}
