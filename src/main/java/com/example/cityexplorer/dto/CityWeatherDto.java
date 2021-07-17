package com.example.cityexplorer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityWeatherDto {
    private double temperature;
    private double feelsLike;
    private int humidity;
    private double windSpeed;
    private LocalDateTime sunrise;
    private LocalDateTime sunset;
}
