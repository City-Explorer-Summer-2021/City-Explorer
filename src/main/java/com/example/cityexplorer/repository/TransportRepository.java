package com.example.cityexplorer.repository;

import com.example.cityexplorer.model.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepository extends JpaRepository<Transport, Long> {
}
