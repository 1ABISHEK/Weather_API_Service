package com.example.weatherapi.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationResponse {
    private String name;
    private String State;
    private String country;
    private double latitude;
    private double longitude;

}
