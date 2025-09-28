package com.example.weatherapi.service;

import com.example.weatherapi.model.response.WeatherResponse;
import com.example.weatherapi.model.response.ForecastResponse;

public interface WeatherService {
    WeatherResponse getCurrentWeather(String city);
    ForecastResponse getForecast(String city, int days);
}
