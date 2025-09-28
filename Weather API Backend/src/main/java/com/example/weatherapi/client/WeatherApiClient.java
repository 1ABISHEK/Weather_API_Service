package com.example.weatherapi.client;

import com.example.weatherapi.model.dto.WeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${weather.provider.weatherapi.url}")
    private String baseUrl;

    @Value("${weather.provider.weatherapi.apikey}")
    private String apiKey;

    public WeatherDto getCurrentWeather(String city) {
        String url = String.format("%s/v1/current.json?key=%s&q=%s",
                baseUrl, apiKey, city);

        return restTemplate.getForObject(url, WeatherDto.class);
    }
}
