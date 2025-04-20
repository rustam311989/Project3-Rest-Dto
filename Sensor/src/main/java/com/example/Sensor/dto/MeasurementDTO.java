package com.example.Sensor.dto;

import com.example.Sensor.model.Sens;
import jakarta.validation.constraints.*;

public class MeasurementDTO {

    @NotNull(message = "sensor should be not empty")
    private SensorDTO owner;

    @NotNull(message = "value should be not empty")
    @Min(value = -100, message="value from -100 to 100")
    @Max(value = 100, message="value from -100 to 100")
    private double value;

    private boolean raining;

    public SensorDTO getOwner() {
        return owner;
    }

    public void setOwner(SensorDTO owner) {
        this.owner = owner;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }
}
