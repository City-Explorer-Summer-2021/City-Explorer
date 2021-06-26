package com.example.cityexplorer.service;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.FoodPlace;

import java.util.List;

public interface FoodPlaceService {

    List<FoodPlace> getList(City city);

    FoodPlace getById(Long id);

    FoodPlace save(FoodPlace foodPlace);

    FoodPlace update(Long id, FoodPlace foodPlace);

    void delete(Long id);
}
