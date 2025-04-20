package com.example.Sensor.controllers;

import com.example.Sensor.dto.MeasurementDTO;
import com.example.Sensor.model.Measurement;
import com.example.Sensor.services.MeasurementService;
import com.example.Sensor.services.SensorService;
import com.example.Sensor.util.MeasErrorResponse;
import com.example.Sensor.util.MeasurementNotCreatedException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MeasController {

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final SensorService sensorService;

    @Autowired
    public MeasController(MeasurementService measurementService, ModelMapper modelMapper, SensorService sensorService) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.sensorService = sensorService;
    }

    @PostMapping("/measurements/add") // аннотация, которая показывает, что сервер получает данные, а не отдает
    public ResponseEntity<HttpStatus> addMeas(@RequestBody @Valid MeasurementDTO dto,
                                             BindingResult bindingResult){
        System.out.println(dto.getOwner().getName());
        if(sensorService.findByName(dto.getOwner().getName())==null){
            throw new MeasurementNotCreatedException("sensor " + dto.getOwner().getName() +
                    " does not exist");
        }
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(e->
                    errorMsg.append(e.getField())
                            .append(" - ")
                            .append(e.getDefaultMessage())
                            .append("; "));

            throw new MeasurementNotCreatedException(errorMsg.toString());
        }
        measurementService.save(convertToMeasurement(dto));

        // Отправляем HTTP ответ с пустым телом и со статусом 200
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/measurements")
    public List<MeasurementDTO> getAllMeasurement() {
        return measurementService.findAll().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList()); // Jackson автоматически конвертирует объекты в JSON
    }

    @GetMapping("/measurements/rainyDaysCount")
    public int getRainyDaysCount() {
        return measurementService.rainyDaysCount(); // Jackson автоматически конвертирует объекты в JSON
    }

    @ExceptionHandler
    private ResponseEntity<MeasErrorResponse> handleException(MeasurementNotCreatedException e) {
        MeasErrorResponse response = new MeasErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // BAD_REQUEST - 400 статус
    }

    private Measurement convertToMeasurement(MeasurementDTO dto) {
        Measurement meas =  modelMapper.map(dto, Measurement.class);
        return meas;
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

}
