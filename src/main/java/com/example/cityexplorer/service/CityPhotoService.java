package com.example.cityexplorer.service;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.CityPhoto;

import java.util.List;


public interface CityPhotoService {
    List<CityPhoto> getList(City city);
    void save(CityPhoto photo);
    void delete(CityPhoto photo);
}
