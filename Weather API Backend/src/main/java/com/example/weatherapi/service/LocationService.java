package com.example.weatherapi.service;

import com.example.weatherapi.model.response.LocationResponse;
import java.util.List;

public interface LocationService {
    List<LocationResponse> searchLocations(String query);
}
