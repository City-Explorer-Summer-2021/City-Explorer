package com.example.cityexplorer.service;

import com.example.cityexplorer.model.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> getList();

    Hotel getById(Long id);

    Hotel save(Hotel hotel);

    Hotel update(Long id, Hotel hotel);

    Hotel delete(Long id);
}
