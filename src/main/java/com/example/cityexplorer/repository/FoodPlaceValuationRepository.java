package com.example.cityexplorer.repository;

import com.example.cityexplorer.model.FoodPlace;
import com.example.cityexplorer.model.FoodPlaceValuation;
import com.example.cityexplorer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodPlaceValuationRepository extends JpaRepository<FoodPlaceValuation, Long> {

    List<FoodPlaceValuation> findAllByFoodPlace(FoodPlace foodPlace);

    Optional<FoodPlaceValuation> findByFoodPlaceAndUser(FoodPlace foodPlace, User user);

    Optional<Long> countByFoodPlace(FoodPlace foodPlace);

    @Query(value = "SELECT AVG(value) FROM food_place_valuation WHERE food_place_id = ?1",
            nativeQuery = true)
    Optional<Double> getAvgValuationByFoodPlaseId(Long foodPlaceId);

}
