package com.example.cityexplorer.controller;

import com.example.cityexplorer.exception.ErrorMessages;
import com.example.cityexplorer.model.FoodPlaceValuation;
import com.example.cityexplorer.service.FoodPlaceValuationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class FoodPlaceValuationController {
    private final FoodPlaceValuationService foodPlaceValuationService;

    @Autowired
    public FoodPlaceValuationController(FoodPlaceValuationService foodPlaceValuationService) {
        this.foodPlaceValuationService = foodPlaceValuationService;
    }

    @PostMapping("/cities/{cityId}/foodplaces/{foodplaceId}/valuations")
    @PreAuthorize("hasAuthority('USER')")
    public String addValuation(
            @PathVariable("cityId") Long cityId,
            @PathVariable("foodplaceId") Long foodplaceId,
            FoodPlaceValuation foodPlaceValuation) {
        Assert.notNull(cityId, ErrorMessages.NULL_CITY_ID.getErrorMessage());
        Assert.notNull(foodplaceId, ErrorMessages.NULL_FOOD_PLACE_ID.getErrorMessage());
        Assert.notNull(foodPlaceValuation, ErrorMessages.NULL_FOOD_PLACE_VALUATION_OBJECT.getErrorMessage());

        foodPlaceValuationService.save(foodPlaceValuation);

        return String.format("redirect:/cities/%d/foodplaces/%d", cityId, foodplaceId);
    }

    @PutMapping("/cities/{cityId}/foodplaces/{foodplaceId}/valuations")
    @PreAuthorize("hasAuthority('USER')")
    public String updateValuation(
            @PathVariable("cityId") Long cityId,
            @PathVariable("foodplaceId") Long foodplaceId,
            FoodPlaceValuation foodPlaceValuation) {
        Assert.notNull(cityId, ErrorMessages.NULL_CITY_ID.getErrorMessage());
        Assert.notNull(foodplaceId, ErrorMessages.NULL_FOOD_PLACE_ID.getErrorMessage());
        Assert.notNull(foodPlaceValuation, ErrorMessages.NULL_FOOD_PLACE_VALUATION_OBJECT.getErrorMessage());

        foodPlaceValuationService.update(foodPlaceValuation.getId(), foodPlaceValuation);

        return String.format("redirect:/cities/%d/foodplaces/%d", cityId, foodplaceId);
    }
}
