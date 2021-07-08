package com.example.cityexplorer.repository;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.FoodPlace;
import com.example.cityexplorer.projection.FoodPlaceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodPlaceRepository extends JpaRepository<FoodPlace, Long> {

    List<FoodPlace> findAllByCity(City city);

    @Query(value = "WITH fp AS (SELECT id, name, description, address FROM food_place WHERE city_id = ?1) " +
            "SELECT fp.id, fp.name, fp.description, fp.address, " +
            "CASE WHEN v.votesNumer IS NOT NULL THEN v.votesNumer ELSE 0 END AS votesNumer, " +
            "CASE WHEN v.avgValue IS NOT NULL THEN ROUND(v.avgValue, 1) ELSE 0 END AS avgValue " +
            "FROM fp " +
            "LEFT JOIN (SELECT food_place_id, COUNT(*) AS votesNumer, AVG(value) AS avgValue " +
            "FROM food_place_valuation " +
            "WHERE food_place_id IN (SELECT id FROM fp) " +
            "GROUP BY food_place_id) AS v ON fp.id = v.food_place_id",
            nativeQuery = true)
    List<FoodPlaceProjection> findAllForCity(City city);

}
