// WeatherResponse.java
package com.example.weatherapi.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherResponse {
    private String location;
    private String condition;
    private double temperature;
    private double feels_like;
    private double humidity;
    private double windSpeed;
    private double minTemp;
    private double maxTemp;
    private String sunrise;
    private String sunset;
    private String source; // which provider returned this
}
