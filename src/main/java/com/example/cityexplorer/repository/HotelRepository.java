package com.example.cityexplorer.repository;

import com.example.cityexplorer.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
