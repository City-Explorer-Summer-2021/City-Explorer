package com.example.cityexplorer.service;

import com.example.cityexplorer.model.TransportCategory;

import java.util.List;

public interface TransportCategoryService {

    List<TransportCategory> getList();

    TransportCategory getById(Long id);

    TransportCategory save(TransportCategory category);

    TransportCategory update(Long id, TransportCategory category);

    void delete(Long id);
}
