package com.example.cityexplorer.repository;

import com.example.cityexplorer.model.Attraction;
import com.example.cityexplorer.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {

    List<Attraction> findAllByCity(City city);

}
