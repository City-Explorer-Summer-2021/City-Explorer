package com.example.cityexplorer.controller;

import com.example.cityexplorer.model.Attraction;
import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.CityPhoto;
import com.example.cityexplorer.model.Event;
import com.example.cityexplorer.model.FoodPlace;
import com.example.cityexplorer.model.Hotel;
import com.example.cityexplorer.model.Transport;
import com.example.cityexplorer.model.User;
import com.example.cityexplorer.service.AttractionService;
import com.example.cityexplorer.service.CityPhotoService;
import com.example.cityexplorer.service.CityService;
import com.example.cityexplorer.service.EventService;
import com.example.cityexplorer.service.FoodPlaceService;
import com.example.cityexplorer.service.HotelService;
import com.example.cityexplorer.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value={"/", "/home"})
public class HomeController {

    private final CityService cityService;
    private final CityPhotoService cityPhotoService;
    private final TransportService transportService;
    private final HotelService hotelService;
    private final AttractionService attractionService;
    private final EventService eventService;
    private final FoodPlaceService foodPlaceService;

    @Autowired
    public HomeController(
            CityService cityService,
            CityPhotoService cityPhotoService,
            TransportService transportService,
            HotelService hotelService,
            AttractionService attractionService,
            EventService eventService,
            FoodPlaceService foodPlaceService){
        this.cityService = cityService;
        this.cityPhotoService = cityPhotoService;
        this.transportService = transportService;
        this.hotelService = hotelService;
        this.attractionService = attractionService;
        this.eventService = eventService;
        this.foodPlaceService = foodPlaceService;
    }

    @GetMapping
    public String openMainPageAndLoadDefaultCity(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);

        City city = cityService.getById(1L);
        model.addAttribute("city", city);

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
}
