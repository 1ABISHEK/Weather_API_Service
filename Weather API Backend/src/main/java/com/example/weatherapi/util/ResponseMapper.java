package com.example.weatherapi.util;

import com.example.weatherapi.model.dto.LocationDto;
import com.example.weatherapi.model.dto.WeatherDto;
import com.example.weatherapi.model.response.LocationResponse;
import com.example.weatherapi.model.response.WeatherResponse;

public class ResponseMapper {

    public static WeatherResponse toWeatherResponse(WeatherDto dto, String source) {
        return WeatherResponse.builder()
                .location(dto.getLocation())
                .condition(dto.getCondition())
                .temperature(dto.getTemperature())
                .feels_like(dto.getFeels_like())
                .humidity(dto.getHumidity())
                .windSpeed(dto.getWindSpeed())
                .minTemp(dto.getMinTemp())
                .maxTemp(dto.getMaxTemp())
                .sunrise(dto.getSunrise())
                .sunset(dto.getSunset())
                .source(source)
                .build();
    }

    public static LocationResponse toLocationResponse(LocationDto dto) {
        return LocationResponse.builder()
                .name(dto.getName())
                .State(dto.getState())
                .country(dto.getCountry())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();
    }
}
