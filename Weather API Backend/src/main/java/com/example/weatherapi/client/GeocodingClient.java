package com.example.weatherapi.client;

import com.example.weatherapi.model.dto.LocationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeocodingClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${weather.provider.geocoding.url}")
    private String baseUrl;

    @Value("${weather.provider.geocoding.apikey}")
    private String apiKey;

    public List<LocationDto> searchLocations(String query) {
        String url = String.format("%s/geo/1.0/direct?q=%s&limit=5&appid=%s",
                baseUrl, query, apiKey);

        LocationDto[] response = restTemplate.getForObject(url, LocationDto[].class);
        return Arrays.asList(response != null ? response : new LocationDto[0]);
    }
}
