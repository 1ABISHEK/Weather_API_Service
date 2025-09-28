package com.example.weatherapi.controller;

import com.example.weatherapi.model.response.LocationResponse;
import com.example.weatherapi.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/search")
    public List<LocationResponse> searchLocations(@RequestParam String q) {
        return locationService.searchLocations(q);
    }
}
