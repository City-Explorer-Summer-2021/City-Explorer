package com.example.cityexplorer.repository;

import com.example.cityexplorer.model.FoodPlace;
import com.example.cityexplorer.model.FoodPlaceValuation;
import com.example.cityexplorer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodPlaceValuationRepository extends JpaRepository<FoodPlaceValuation, Long> {

    List<FoodPlaceValuation> findAllByFoodPlace(FoodPlace foodPlace);

    Optional<FoodPlaceValuation> findByFoodPlaceAndUser(FoodPlace foodPlace, User user);

}
