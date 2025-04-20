package com.example.Sensor.repositories;

import com.example.Sensor.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    @Query(value = "SELECT * FROM measurement WHERE raining=true",nativeQuery = true)
    public List<Measurement> findRainyDays();

}
