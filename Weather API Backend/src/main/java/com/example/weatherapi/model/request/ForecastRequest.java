// ForecastRequest.java
package com.example.weatherapi.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ForecastRequest {
    @NotBlank(message = "Location cannot be blank")
    private String location;

    @Min(value = 1, message = "Days must be at least 1")
    @Max(value = 6, message = "Days cannot exceed 6")
    private int days;
}
