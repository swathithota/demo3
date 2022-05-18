package com.example.demo3.repo;

import com.example.demo3.domain.MetricReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetricRepo extends JpaRepository<MetricReading, String> {

    List<MetricReading> findAll();

    List<MetricReading> findAllBySensorId(String sensorID);
    List<MetricReading> findAllById(String sensorID);
}
