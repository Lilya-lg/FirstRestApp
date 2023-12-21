package uz.alishev.spring.FirstRestApp.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import uz.alishev.spring.FirstRestApp.dto.MeasurementDTO;
import uz.alishev.spring.FirstRestApp.models.Measurement;
import uz.alishev.spring.FirstRestApp.services.MeasurementService;
import uz.alishev.spring.FirstRestApp.services.SensorService;
import uz.alishev.spring.FirstRestApp.util.MeasurementNotCreatedException;
import uz.alishev.spring.FirstRestApp.util.SensorErrorResponse;
import uz.alishev.spring.FirstRestApp.util.SensorNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final SensorService sensorService;

    public MeasurementController(MeasurementService measurementService, SensorService sensorService) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
    }
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody MeasurementDTO measurementDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors  = bindingResult.getFieldErrors();
            for(FieldError error:errors){
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }
            throw new MeasurementNotCreatedException(errorMessage.toString());
        }
        measurementService.save(convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(MeasurementNotCreatedException e){
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotFoundException e){
        SensorErrorResponse response = new SensorErrorResponse(
                "Sensor not found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @GetMapping()
    public List<Measurement> getMeasurements(){
        return measurementService.findAll();
    }
    @GetMapping("/rainyDaysCount")
    public List<Measurement> getRainyDays(){
        return measurementService.getRainyDays();
    }
    public Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        Measurement measurement = new Measurement();
        measurement.setTemperature(measurementDTO.getTemperature());
        measurement.setRaining(measurementDTO.isRaining());
        measurement.setSensor(sensorService.findByName(measurementDTO.getSensor().getName()));
        measurementService.enrich(measurement);
        return measurement;
    }
}
