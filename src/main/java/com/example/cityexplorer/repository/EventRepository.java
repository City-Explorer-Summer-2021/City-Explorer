package com.example.cityexplorer.repository;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByCity(City city);

}
