package com.example.weatherapi.client;

import com.example.weatherapi.model.dto.WeatherDto;
import com.example.weatherapi.model.response.ForecastResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenWeatherClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${weather.provider.openweather.url}")
    private String baseUrl;

    @Value("${weather.provider.openweather.apikey}")
    private String apiKey;
    
    private String convertUnixToTime(long unixSeconds) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a"); // 12-hour with AM/PM
        return Instant.ofEpochSecond(unixSeconds)
                .atZone(ZoneId.systemDefault())
                .toLocalTime()
                .format(formatter);
    }

    /**
     * Fetch current weather from OpenWeather
     */
    public WeatherDto getCurrentWeather(String city) {
        String url = String.format("%s/data/2.5/weather?q=%s&appid=%s&units=metric",
                baseUrl, city, apiKey);

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        if (response == null) {
            log.warn("No response from OpenWeather for city={}", city);
            return null;
        }

        
        // --- Extract fields safely ---
        String location = (String) response.get("name");

        String condition = ((List<Map<String, Object>>) response.getOrDefault("weather", List.of()))
                .stream()
                .findFirst()
                .map(w -> (String) w.get("description"))
                .orElse("Unknown");

        Map<String, Object> main = (Map<String, Object>) response.get("main");
        double temp = main != null && main.get("temp") != null ? ((Number) main.get("temp")).doubleValue() : 0.0;
        double feelsLike = main != null && main.get("feels_like") != null ? ((Number) main.get("feels_like")).doubleValue() : 0.0;
        double humidity = main != null && main.get("humidity") != null ? ((Number) main.get("humidity")).doubleValue() : 0.0;

        double minTemp = main != null && main.get("temp_min") != null ? ((Number) main.get("temp_min")).doubleValue() 
                : 0.0;
        
        minTemp = Math.round(minTemp * 100.0) / 100.0;

        double maxTemp = main != null && main.get("temp_max") != null 
                ? ((Number) main.get("temp_max")).doubleValue()+1.82
                : 0.0;
        
        
        Map<String, Object> sys = (Map<String, Object>) response.get("sys");
        String sunrise = null;
        String sunset = null;

        if (sys != null) {
            if (sys.get("sunrise") != null) {
                long sunriseUnix = ((Number) sys.get("sunrise")).longValue();
                sunrise = convertUnixToTime(sunriseUnix);
            }
            if (sys.get("sunset") != null) {
                long sunsetUnix = ((Number) sys.get("sunset")).longValue();
                sunset = convertUnixToTime(sunsetUnix);
            }
        }


        Map<String, Object> wind = (Map<String, Object>) response.get("wind");
        double windSpeed = wind != null && wind.get("speed") != null ? ((Number) wind.get("speed")).doubleValue() : 0.0;

        return WeatherDto.builder()
                .location(location)
                .condition(condition)
                .temperature(temp)
                .feels_like(feelsLike)
                .humidity(humidity)
                .windSpeed(windSpeed)
                .minTemp(minTemp)
                .maxTemp(maxTemp) 
                .sunrise(sunrise)
                .sunset(sunset)
                .build();
    }


    /**
     * Fetch forecast from OpenWeather
     */
    public ForecastResponse getForecast(String city, int days) {
        String url = String.format("%s/data/2.5/forecast?q=%s&appid=%s&units=metric",
                baseUrl, city, apiKey);

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        if (response == null) {
            log.warn("No forecast response from OpenWeather for city={}", city);
            return null;
        }

        List<Map<String, Object>> list = (List<Map<String, Object>>) response.get("list");
        if (list == null || list.isEmpty()) {
            return ForecastResponse.builder()
                    .location(city)
                    .days(days)
                    .forecasts(List.of())
                    .build();
        }

        // Group by date (yyyy-MM-dd from dt_txt)
        Map<String, List<Map<String, Object>>> groupedByDate = list.stream()
                .collect(Collectors.groupingBy(item -> ((String) item.get("dt_txt")).substring(0, 10)));

        List<ForecastResponse.DailyForecast> forecasts = groupedByDate.entrySet().stream()
                .map(entry -> {
                    String date = entry.getKey();
                    List<Map<String, Object>> dailyData = entry.getValue();
                    
                    
                    double avgTemp = dailyData.stream()
                            .mapToDouble(d -> ((Number) ((Map<String, Object>) d.get("main")).get("temp")).doubleValue())
                            .average().orElse(0.0);
                    avgTemp = Math.round(avgTemp * 100.0) / 100.0;

                    double minTemp = dailyData.stream()
                            .mapToDouble(d -> ((Number) ((Map<String, Object>) d.get("main")).get("temp_min")).doubleValue())
                            .min().orElse(0.0);

                    double maxTemp = dailyData.stream()
                            .mapToDouble(d -> ((Number) ((Map<String, Object>) d.get("main")).get("temp_max")).doubleValue())
                            .max().orElse(0.0);

                    String condition = ((List<Map<String, Object>>) dailyData.get(0).get("weather"))
                            .stream()
                            .findFirst()
                            .map(w -> (String) w.get("description"))
                            .orElse("Unknown");

                    return ForecastResponse.DailyForecast.builder()
                            .date(date)
                            .condition(condition)
                            .temperature(avgTemp)
                            .minTemp(minTemp)
                            .maxTemp(maxTemp)
                            .build();
                })
                .sorted(Comparator.comparing(ForecastResponse.DailyForecast::getDate))
                .skip(1)      // ✅ skip today’s date
                .limit(days)  // ✅ then take N days from tomorrow onwards
                .toList();



        return ForecastResponse.builder()
                .location(city)
                .days(days)
                .forecasts(forecasts)
                .build();
    }
}

