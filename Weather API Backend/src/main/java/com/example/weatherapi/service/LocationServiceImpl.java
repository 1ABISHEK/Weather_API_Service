package com.example.weatherapi.service;

import com.example.weatherapi.client.GeocodingClient;
import com.example.weatherapi.model.dto.LocationDto;
import com.example.weatherapi.model.response.LocationResponse;
import com.example.weatherapi.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final GeocodingClient geocodingClient;

    @Override
    @Cacheable(value = "locations", key = "#query")
    public List<LocationResponse> searchLocations(String query) {
        List<LocationDto> dtoList = geocodingClient.searchLocations(query);

        return dtoList.stream()
                .map(dto -> LocationResponse.builder()
                        .name(dto.getName())
                        .State(dto.getState())
                        .country(dto.getCountry())
                        .latitude(dto.getLatitude())
                        .longitude(dto.getLongitude())
                        .build())
                .collect(Collectors.toList());
    }
}
