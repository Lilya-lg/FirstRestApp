package uz.alishev.spring.FirstRestApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name="Measurements")
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "temperature", nullable = false)
    @Min(value = -100 , message = "Value should be greater then equal to -100")
    @Max(value = 100 , message = "Value should be less then equal to 100")
    @NotNull
    private float temperature;
    @Column(name = "raining")
    @NotNull
    private boolean raining;
    @Column(name = "created_at")
    private Date created_at;
    @ManyToOne
    @JoinColumn(name="sensor_id",referencedColumnName = "id")
    private Sensor sensor;
    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Measurement() {
        this.temperature = temperature;
        this.raining = raining;
        this.sensor = sensor;
    }

    public Measurement(float temperature, boolean raining) {
        this.temperature = temperature;
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
