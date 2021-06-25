package com.example.cityexplorer.service;

import com.example.cityexplorer.model.HotelCategory;

import java.util.List;

public interface HotelCategoryService {

    List<HotelCategory> getList();

    HotelCategory getById(Long id);

    HotelCategory save(HotelCategory category);

    HotelCategory update(Long id, HotelCategory category);

    void delete(Long id);
}
