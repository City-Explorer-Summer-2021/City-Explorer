package com.example.cityexplorer.controller;

import com.example.cityexplorer.projection.AvgValuationProjection;
import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.FoodPlace;
import com.example.cityexplorer.model.FoodPlaceValuation;
import com.example.cityexplorer.model.User;
import com.example.cityexplorer.service.FoodPlaceService;
import com.example.cityexplorer.service.FoodPlaceValuationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final FoodPlaceValuationService valuationService;

    @Autowired
    public FoodPlaceController(
            FoodPlaceService foodPlaceService,
            FoodPlaceValuationService valuationService) {
        this.foodPlaceService = foodPlaceService;
        this.valuationService = valuationService;
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
        model.addAttribute("user", user);
        model.addAttribute("isAdmin", user != null && user.isAdmin());

        FoodPlaceValuation valuation = valuationService.getByByFoodPlaceAndUser(foodPlace, user);
        model.addAttribute("valuation", valuation);
        model.addAttribute("isNewValuation", valuation.getValue() < 1);

        AvgValuationProjection avgValuation = valuationService.getAvgValuationByFoodPlaseId(foodPlace.getId());
        model.addAttribute("valuationAvg",
                String.format("%.1f", avgValuation.getAvgValue()));
        model.addAttribute("valuationCount",
                avgValuation.getVotesNumer());

        return "foodplace";
    }

    @GetMapping("/cities/{cityId}/foodplaces/{foodPlaceId}/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getFoodPlaceEditPage(
            @PathVariable("cityId") City city,
            @PathVariable("foodPlaceId") FoodPlace foodPlace,
            Model model) {
        model.addAttribute("foodPlace", foodPlace);
        model.addAttribute("city", city);
        model.addAttribute("isNew", false);
        return "foodplace_edit";
    }

    @GetMapping("/cities/{cityId}/foodplaces/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getFoodPlaceAddPage(
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getFoodPlaceDeletePage(
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveNewFoodPlace(@PathVariable("cityId") Long cityId,
                                   FoodPlace foodPlace) {
        foodPlaceService.save(foodPlace);
        return String.format("redirect:/cities/%d/foodplaces", cityId);
    }

    @PutMapping(value = "/cities/{cityId}/foodplaces/{foodPlaceId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateFoodPlace(@PathVariable("cityId") Long cityId,
                                  @PathVariable("foodPlaceId") Long foodPlaceId,
                                  FoodPlace foodPlace) {
        foodPlaceService.update(foodPlaceId, foodPlace);
        return String.format("redirect:/cities/%d/foodplaces", cityId);
    }

    @DeleteMapping("/cities/{cityId}/foodplaces/{foodPlaceId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteFoodPlace(@PathVariable("cityId") Long cityId,
                                  @PathVariable("foodPlaceId") Long foodPlaceId) {
        foodPlaceService.delete(foodPlaceId);
        return String.format("redirect:/cities/%d/foodplaces", cityId);
    }
}
