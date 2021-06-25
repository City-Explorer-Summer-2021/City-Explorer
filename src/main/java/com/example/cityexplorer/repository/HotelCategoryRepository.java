package com.example.cityexplorer.repository;

import com.example.cityexplorer.model.HotelCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelCategoryRepository extends JpaRepository<HotelCategory, Long> {
}
