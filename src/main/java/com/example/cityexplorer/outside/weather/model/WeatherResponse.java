package com.example.cityexplorer.outside.weather.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {
    private Main main;
    private Wind wind;
    private Sys sys;
}
