package com.example.cityexplorer.repository;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findAllByCityOrderById(City city);

}
