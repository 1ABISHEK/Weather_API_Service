package com.example.weatherapi.service;

import com.example.weatherapi.client.OpenWeatherClient;
import com.example.weatherapi.client.WeatherApiClient;
import com.example.weatherapi.model.dto.WeatherDto;
import com.example.weatherapi.model.response.ForecastResponse;
import com.example.weatherapi.model.response.WeatherResponse;
import com.example.weatherapi.util.ResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final OpenWeatherClient openWeatherClient;
    private final WeatherApiClient weatherApiClient; // for fallback later if needed

    @Override
    @Cacheable(value = "weather", key = "#city")
    public WeatherResponse getCurrentWeather(String city) {
        log.info("Request received: /weather/current for city={}", city);

        WeatherDto dto = openWeatherClient.getCurrentWeather(city);
        if (dto == null) {
            log.warn("No data from OpenWeather for city={}", city);
            return null; // or throw a custom exception if you want
        }

        WeatherResponse response = ResponseMapper.toWeatherResponse(dto, "OpenWeather");

        log.info("Response sent: {}", response);
        return response;
    }

    @Override
    @Cacheable(value = "forecast", key = "#city + '_' + #days")
    public ForecastResponse getForecast(String city, int days) {
        log.info("Request received: /weather/forecast for city={}, days={}", city, days);

        ForecastResponse response = openWeatherClient.getForecast(city, days);
        if (response == null) {
            log.warn("No forecast data from OpenWeather for city={}, days={}", city, days);
            return ForecastResponse.builder()
                    .location(city)
                    .days(days)
                    .forecasts(java.util.List.of()) // return empty list instead of null
                    .build();
        }

        log.info("Response sent: {}", response);
        return response;
    }
}
