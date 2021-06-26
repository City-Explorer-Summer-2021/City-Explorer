package com.example.cityexplorer.service;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.Transport;

import java.util.List;

public interface TransportService {

    List<Transport> getList(City city);

    Transport getById(Long id);

    Transport save(Transport transport);

    Transport update(Long id, Transport transport);

    void delete(Long id);
}
