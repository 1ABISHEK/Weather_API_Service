// ForecastResponse.java
package com.example.weatherapi.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ForecastResponse {
    private String location;
    private int days;
    private List<DailyForecast> forecasts;

    @Data
    @Builder
    public static class DailyForecast {
        private String date;
        private double temperature;
        private String condition;
        private double minTemp;
        private double maxTemp;
    }
}
