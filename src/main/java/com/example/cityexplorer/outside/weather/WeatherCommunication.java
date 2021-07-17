package com.example.cityexplorer.outside.weather;

import com.example.cityexplorer.outside.weather.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class WeatherCommunication {
    private final RestTemplate restTemplate;
    private final String URL_1 = "https://api.openweathermap.org/data/2.5/weather?lat=";
    private final String URL_2 = "&lon=";
    private final String URL_3 = "&units=metric&appid=bf5b5cadff22dad4ce932dbd10a8b5ad";

    @Autowired
    public WeatherCommunication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherResponse getWeatherByCoords(BigDecimal lat, BigDecimal lon) {
        String targetUrl = URL_1 + lat + URL_2 + lon + URL_3;
        WeatherResponse result = null;
        try {
            ResponseEntity<WeatherResponse> weatherResponseEntity =
                    restTemplate.exchange(
                            targetUrl,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<WeatherResponse>() {
                            });
            result = weatherResponseEntity.getBody();
        } catch (ResourceAccessException e){
            e.printStackTrace();
        }
        return result;
    }
}
