package com.example.cityexplorer.service;

import com.example.cityexplorer.dto.HotelFilterDto;
import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> getList(City city);

    Hotel getById(Long id);

    Hotel save(Hotel hotel);

    Hotel update(Long id, Hotel hotel);

    void delete(Long id);

    HotelFilterDto newFilter(City city);

    List<Hotel> getFilteredList(City city, HotelFilterDto hotelFilter);
}
