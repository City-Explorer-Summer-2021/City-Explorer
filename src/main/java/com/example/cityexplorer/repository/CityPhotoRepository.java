package com.example.cityexplorer.repository;

import com.example.cityexplorer.model.Attraction;
import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.CityPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityPhotoRepository extends JpaRepository<CityPhoto, Long> {
    List<CityPhoto> findAllByCity(City city);
}
