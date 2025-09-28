// LocationDto.java
package com.example.weatherapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationDto {
    private String name;
    private String state;
    private String country;
    @JsonProperty("lat")
    private double latitude;

    @JsonProperty("lon")
    private double longitude;
}
