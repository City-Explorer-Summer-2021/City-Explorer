package com.example.cityexplorer.controller;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.FoodPlace;
import com.example.cityexplorer.model.Hotel;
import com.example.cityexplorer.model.User;
import com.example.cityexplorer.service.EventService;
import com.example.cityexplorer.service.FoodPlaceService;
import com.example.cityexplorer.service.HotelService;
import com.example.cityexplorer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class FoodPlacesController {

    private final FoodPlaceService foodPlaceService;
    private final UserService userService;

    @Autowired
    public FoodPlacesController(FoodPlaceService foodPlaceService, UserService userService) {
        this.foodPlaceService = foodPlaceService;
        this.userService = userService;
    }

    @GetMapping("/cities/{cityId}/foodPlaces")
    public String getList(
            @PathVariable("cityId") City city,
            @AuthenticationPrincipal User user,
            Model model) {
        List<FoodPlace> foodPlaces = foodPlaceService.getList(city);
        model.addAttribute("foodPlaces", foodPlaces);
        model.addAttribute("city", city);
        return "food_places";
    }

    @GetMapping("/cities/{cityId}/foodPlaces/{foodPlaceId}")
    public String getFoodPlacePage(
            @PathVariable("cityId") City city,
            @PathVariable("foodPlaceId") FoodPlace foodPlace,
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("foodPlace", foodPlace);
        model.addAttribute("city", city);
        return "food_place";
    }

}
