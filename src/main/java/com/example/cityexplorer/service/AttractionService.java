package com.example.cityexplorer.service;

import com.example.cityexplorer.model.Attraction;
import com.example.cityexplorer.model.City;

import java.util.List;

public interface AttractionService {

    List<Attraction> getList(City city);

    Attraction getById(Long id);

    Attraction save(Attraction attraction);

    Attraction update(Long id, Attraction attraction);

    void delete(Long id);
}
