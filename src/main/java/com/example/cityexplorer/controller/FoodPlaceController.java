package com.example.cityexplorer.controller;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.FoodPlace;
import com.example.cityexplorer.model.Hotel;
import com.example.cityexplorer.model.User;
import com.example.cityexplorer.service.FoodPlaceService;
import com.example.cityexplorer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
public class FoodPlaceController {

    private final FoodPlaceService foodPlaceService;
    private final UserService userService;

    @Autowired
    public FoodPlaceController(FoodPlaceService foodPlaceService, UserService userService) {
        this.foodPlaceService = foodPlaceService;
        this.userService = userService;
    }

    @GetMapping("/cities/{cityId}/foodplaces")
    public String getList(
            @PathVariable("cityId") City city,
            @AuthenticationPrincipal User user,
            Model model) {
        List<FoodPlace> foodPlaces = foodPlaceService.getList(city);
        model.addAttribute("foodPlaces", foodPlaces);
        model.addAttribute("city", city);
        model.addAttribute("isAdmin", user != null && user.isAdmin());
        return "foodplaces";
    }

    @GetMapping("/cities/{cityId}/foodplaces/{foodPlaceId}")
    public String getFoodPlacePage(
            @PathVariable("cityId") City city,
            @PathVariable("foodPlaceId") FoodPlace foodPlace,
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("foodPlace", foodPlace);
        model.addAttribute("city", city);
        model.addAttribute("isDeleting", false);
        model.addAttribute("isAdmin", user != null && user.isAdmin());
        return "foodplace";
    }

    @GetMapping("/cities/{cityId}/foodplaces/{foodPlaceId}/edit")
    public String getHotelEditPage(
            @PathVariable("cityId") City city,
            @PathVariable("foodPlaceId") FoodPlace foodPlace,
            Model model) {
        model.addAttribute("foodPlace", foodPlace);
        model.addAttribute("city", city);
        model.addAttribute("isNew", false);
        return "foodplace_edit";
    }

    @GetMapping("/cities/{cityId}/foodplaces/add")
    public String getHotelAddPage(
            @PathVariable("cityId") City city,
            Model model) {
        FoodPlace foodPlace = new FoodPlace();
        foodPlace.setCity(city);
        model.addAttribute("foodPlace", foodPlace);
        model.addAttribute("city", city);
        model.addAttribute("isNew", true);
        return "foodplace_edit";
    }

    @GetMapping("/cities/{cityId}/foodplaces/{foodPlaceId}/delete")
    public String getHotelDeletePage(
            @PathVariable("cityId") City city,
            @PathVariable("foodPlaceId") FoodPlace foodPlace,
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("foodPlace", foodPlace);
        model.addAttribute("city", city);
        model.addAttribute("isDeleting", true);
        model.addAttribute("isAdmin", user != null && user.isAdmin());
        return "foodplace";
    }

    @PostMapping("/cities/{cityId}/foodplaces")
    public String saveNewHotel(@AuthenticationPrincipal User user,
                               @PathVariable("cityId") Long cityId,
                               FoodPlace foodPlace) {
        foodPlaceService.save(foodPlace);
        return String.format("redirect:/cities/%d/foodplaces", cityId);
    }

    @PutMapping(value = "/cities/{cityId}/foodplaces/{foodPlaceId}")
    public String updateHotel(@PathVariable("cityId") Long cityId,
                              @PathVariable("foodPlaceId") Long foodPlaceId,
                              FoodPlace foodPlace) {
        foodPlaceService.update(foodPlaceId, foodPlace);
        return String.format("redirect:/cities/%d/foodplaces", cityId);
    }

    @DeleteMapping("/cities/{cityId}/foodplaces/{foodPlaceId}")
    public String deleteHotel(@PathVariable("cityId") Long cityId,
                              @PathVariable("foodPlaceId") Long foodPlaceId) {
        foodPlaceService.delete(foodPlaceId);
        return String.format("redirect:/cities/%d/foodplaces", cityId);
    }
}