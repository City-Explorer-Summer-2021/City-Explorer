package com.example.cityexplorer.repository;

import com.example.cityexplorer.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
