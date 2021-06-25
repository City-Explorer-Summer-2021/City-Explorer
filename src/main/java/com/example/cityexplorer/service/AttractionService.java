package com.example.cityexplorer.service;

import com.example.cityexplorer.model.Attraction;

import java.util.List;

public interface AttractionService {

    List<Attraction> getList();

    Attraction getById(Long id);

    Attraction save(Attraction attraction);

    Attraction update(Long id, Attraction attraction);

    void delete(Long id);
}
