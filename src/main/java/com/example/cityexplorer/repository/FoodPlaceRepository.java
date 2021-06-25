package com.example.cityexplorer.repository;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.FoodPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodPlaceRepository extends JpaRepository<FoodPlace, Long> {

    List<FoodPlace> findAllByCity(City city);

}
