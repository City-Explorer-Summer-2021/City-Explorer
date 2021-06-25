package com.example.cityexplorer.service;

import com.example.cityexplorer.model.FoodPlaceValuation;

import java.util.List;

public interface FoodPlaceValuationService {

    List<FoodPlaceValuation> getList();

    FoodPlaceValuation getById(Long id);

    FoodPlaceValuation save(FoodPlaceValuation valuation);

    FoodPlaceValuation update(Long id, FoodPlaceValuation valuation);

    void delete(Long id);
}
