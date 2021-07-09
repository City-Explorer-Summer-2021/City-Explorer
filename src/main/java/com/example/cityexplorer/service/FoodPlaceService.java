package com.example.cityexplorer.service;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.FoodPlace;
import com.example.cityexplorer.projection.FoodPlaceProjection;

import java.util.List;

public interface FoodPlaceService {

    List<FoodPlace> getList(City city);

    List<FoodPlaceProjection> getListWithValuations(City city);

    FoodPlace getById(Long id);

    FoodPlace save(FoodPlace foodPlace);

    FoodPlace update(Long id, FoodPlace foodPlace);

    void delete(Long id);
}
