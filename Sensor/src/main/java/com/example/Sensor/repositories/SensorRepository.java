package com.example.Sensor.repositories;

import com.example.Sensor.model.Sens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sens, Integer> {

    @Query(value = "SELECT * FROM sensor WHERE name=?",nativeQuery = true)
    public Optional<Sens> findByName(String name);

}
