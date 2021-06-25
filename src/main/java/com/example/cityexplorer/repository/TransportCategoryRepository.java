package com.example.cityexplorer.repository;

import com.example.cityexplorer.model.TransportCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportCategoryRepository extends JpaRepository<TransportCategory, Long> {
}
