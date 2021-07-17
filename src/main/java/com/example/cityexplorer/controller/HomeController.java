package com.example.cityexplorer.controller;

import com.example.cityexplorer.dto.CityDto;
import com.example.cityexplorer.dto.CityWeatherDto;
import com.example.cityexplorer.model.Attraction;
import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.CityPhoto;
import com.example.cityexplorer.model.Event;
import com.example.cityexplorer.model.FoodPlace;
import com.example.cityexplorer.model.Hotel;
import com.example.cityexplorer.model.Transport;
import com.example.cityexplorer.outside.weather.WeatherCommunication;
import com.example.cityexplorer.outside.weather.model.WeatherResponse;
import com.example.cityexplorer.service.AttractionService;
import com.example.cityexplorer.service.CityPhotoService;
import com.example.cityexplorer.service.CityService;
import com.example.cityexplorer.service.EventService;
import com.example.cityexplorer.service.FoodPlaceService;
import com.example.cityexplorer.service.HotelService;
import com.example.cityexplorer.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    private final CityService cityService;
    private final CityPhotoService cityPhotoService;
    private final TransportService transportService;
    private final HotelService hotelService;
    private final AttractionService attractionService;
    private final EventService eventService;
    private final FoodPlaceService foodPlaceService;
    private final WeatherCommunication weatherCommunication;

    @Autowired
    public HomeController(
            CityService cityService,
            CityPhotoService cityPhotoService,
            TransportService transportService,
            HotelService hotelService,
            AttractionService attractionService,
            EventService eventService,
            FoodPlaceService foodPlaceService,
            WeatherCommunication weatherCommunication
    ) {
        this.cityService = cityService;
        this.cityPhotoService = cityPhotoService;
        this.transportService = transportService;
        this.hotelService = hotelService;
        this.attractionService = attractionService;
        this.eventService = eventService;
        this.foodPlaceService = foodPlaceService;
        this.weatherCommunication = weatherCommunication;
    }

    @GetMapping
    public String openMainPageWithDefaultCity(
            @RequestParam(name = "id", required = false) Long id,
            Model model) {

        City city = cityService.getById(Objects.requireNonNullElse(id, 1L));
        model.addAttribute("city", city);

        WeatherResponse weatherResponse = weatherCommunication.getWeatherByCoords(city.getLat(), city.getLon());

        if (weatherResponse != null){
            CityWeatherDto weatherDto = new CityWeatherDto(
                    weatherResponse.getMain().getTemp(),
                    weatherResponse.getMain().getFeels_like(),
                    weatherResponse.getMain().getHumidity(),
                    weatherResponse.getWind().getSpeed(),
                    convertToLocalDateTime(weatherResponse.getSys().getSunrise()),
                    convertToLocalDateTime(weatherResponse.getSys().getSunset())
            );
            model.addAttribute("weather", weatherDto);
        }


        model.addAttribute("lat", city.getLat());
        model.addAttribute("lon", city.getLon());

        if (id != null) {
            CityDto cityDto = new CityDto(city.getId(), city.getName());
            model.addAttribute("cityDto", cityDto);
        }

        List<CityPhoto> cityPhotos = cityPhotoService.getList(city);
        model.addAttribute("cityPhotos", cityPhotos);

        List<Transport> transports = transportService.getList(city);
        model.addAttribute("transports", transports);

        List<Hotel> hotels = hotelService.getList(city);
        model.addAttribute("hotels", hotels);

        List<Attraction> attractions = attractionService.getList(city);
        model.addAttribute("attractions", attractions);

        List<Event> events = eventService.getList(city);
        model.addAttribute("events", events);

        List<FoodPlace> foodPlaces = foodPlaceService.getList(city);
        model.addAttribute("foodPlaces", foodPlaces);

        return "home";
    }

    private LocalDateTime convertToLocalDateTime(long unixTime) {
        long ms = unixTime * 1000;
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(ms),
                TimeZone.getDefault().toZoneId());
    }
}
