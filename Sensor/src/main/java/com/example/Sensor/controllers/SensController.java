package com.example.Sensor.controllers;

import com.example.Sensor.dto.SensorDTO;
import com.example.Sensor.model.Sens;
import com.example.Sensor.services.SensorService;
import com.example.Sensor.util.SensorNotCreatedException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SensController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;


    @Autowired
    public SensController(ModelMapper modelMapper, SensorService sensorService) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/sensors/registration")
    public ResponseEntity<HttpStatus> addSens(@RequestBody @Valid SensorDTO dto,
                                                    BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(e->
                    errorMsg.append(e.getField())
                            .append(" - ")
                            .append(e.getDefaultMessage())
                            .append("; "));

            throw new SensorNotCreatedException(errorMsg.toString());
        }
        sensorService.save(convertToSensor(dto));

        // Отправляем HTTP ответ с пустым телом и со статусом 200
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sens convertToSensor(SensorDTO dto) {
        return modelMapper.map(dto, Sens.class);
    }

}
