package com.example.cityexplorer.repository;

import com.example.cityexplorer.model.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {
}
