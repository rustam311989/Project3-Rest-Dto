package com.example.Sensor.services;

import com.example.Sensor.model.Measurement;
import com.example.Sensor.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measRepository, SensorService sensorService) {
        this.measRepository = measRepository;
        this.sensorService = sensorService;
    }

    @Transactional // мы изменяем данные в БД, поэтому должны пометить метод этой аннотацией
    public void save(Measurement meas) {
        enrichPerson(meas);       // назначаем дополнительные поля на сервере
        measRepository.save(meas);
    }

    private void enrichPerson(Measurement meas) {

        meas.setOwner(sensorService.findByName(meas.getOwner().getName()));
        meas.setCreatedAt(LocalDateTime.now());
    }

    public List<Measurement> findAll() {
        return measRepository.findAll();
    }

    public int rainyDaysCount(){
        return measRepository.findRainyDays().size();
    }
}
