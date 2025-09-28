package com.example.weatherapi.controller;

import com.example.weatherapi.model.response.WeatherResponse;
import com.example.weatherapi.model.response.ForecastResponse;
import com.example.weatherapi.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class WeatherController {
	
    private final WeatherService weatherService;

    @GetMapping("/current")
    public WeatherResponse getCurrentWeather(@RequestParam String location) {
        return weatherService.getCurrentWeather(location);
    }

    @GetMapping("/forecast")
    public ForecastResponse getForecast(@RequestParam String location, @RequestParam int days) {
        return weatherService.getForecast(location, days);
    }
}
