package com.example.cityexplorer.outside.weather.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Main {
    private double temp;
    private double feels_like;
    private int humidity;
}
