package com.example.cityexplorer.repository;

import com.example.cityexplorer.model.FoodPlaceValuation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodPlaceValuationRepository extends JpaRepository<FoodPlaceValuation, Long> {
}
