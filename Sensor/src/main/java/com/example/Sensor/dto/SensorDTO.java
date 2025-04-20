package com.example.Sensor.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {

    @NotEmpty(message = "name should be not empty")
    @Size(min=3, max=30, message="name should be [3,30] characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
