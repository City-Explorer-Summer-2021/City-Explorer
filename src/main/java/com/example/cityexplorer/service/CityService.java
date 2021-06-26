package com.example.cityexplorer.service;

import com.example.cityexplorer.model.City;

import java.util.List;

public interface CityService {

    List<City> getList();

    List<City> getOrderedByNameList();

    City getById(Long id);

    City save(City city);

    City update(Long id, City city);

    void delete(Long id);
}
