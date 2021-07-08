package com.example.cityexplorer.service;

import com.example.cityexplorer.projection.AvgValuationProjection;
import com.example.cityexplorer.model.FoodPlace;
import com.example.cityexplorer.model.FoodPlaceValuation;
import com.example.cityexplorer.model.User;

import java.util.List;

public interface FoodPlaceValuationService {

    List<FoodPlaceValuation> getList(FoodPlace foodPlace);

    FoodPlaceValuation getById(Long id);

    FoodPlaceValuation getByFoodPlaceAndUser(FoodPlace foodPlace, User user);

    AvgValuationProjection getAvgValuationByFoodPlaseId(Long foodPlaceId);

    FoodPlaceValuation save(FoodPlaceValuation valuation);

    FoodPlaceValuation update(Long id, FoodPlaceValuation valuation);

    void delete(Long id);
}
