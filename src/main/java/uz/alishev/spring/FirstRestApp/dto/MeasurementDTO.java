package uz.alishev.spring.FirstRestApp.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import uz.alishev.spring.FirstRestApp.models.Sensor;

public class MeasurementDTO {
    @NotNull
    private Boolean raining;
    @Min(value = -100 , message = "Value should be greater then equal to -100")
    @Max(value = 100 , message = "Value should be less then equal to 100")
    @NotNull
    private Float temperature;

    @ManyToOne
    @JoinColumn(name="sensor_id",referencedColumnName = "id")
    private Sensor sensor;
    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
