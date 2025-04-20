package com.example.Sensor.services;

import com.example.Sensor.model.Sens;
import com.example.Sensor.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SensorService {

    private final SensorRepository sensRepository;

    @Autowired
    public SensorService(SensorRepository sensRepository) {
        this.sensRepository = sensRepository;
    }

    @Transactional
    public void save(Sens sensor) {
        sensRepository.save(sensor);
    }

    public Sens findByName(String name) {
        return sensRepository.findByName(name).orElse(null);
    }
}
