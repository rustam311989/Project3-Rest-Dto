package com.example.Sensor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne//(cascade = CascadeType.ALL)//(fetch = FetchType.EAGER)
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
//    @NotNull(message = "sensor should be not empty")
    private Sens owner;

    @Column(name = "value")
//    @NotNull(message = "value should be not empty")
//    @Min(value = -100, message="value from -100 to 100")
//    @Max(value = 100, message="value from -100 to 100")
    private double value;

    @Column(name = "raining")
    private boolean raining;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Measurement() {}

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sens getOwner() {
        return owner;
    }

    public void setOwner(Sens owner) {
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
