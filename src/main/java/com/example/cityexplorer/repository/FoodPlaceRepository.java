package com.example.cityexplorer.repository;

import com.example.cityexplorer.model.FoodPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodPlaceRepository extends JpaRepository<FoodPlace, Long> {
}