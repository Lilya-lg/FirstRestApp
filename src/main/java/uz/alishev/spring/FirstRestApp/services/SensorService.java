package uz.alishev.spring.FirstRestApp.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.alishev.spring.FirstRestApp.models.Sensor;
import uz.alishev.spring.FirstRestApp.repositories.SensorRepository;
import uz.alishev.spring.FirstRestApp.util.SensorNotFoundException;

import java.util.Optional;

@Service
@Transactional
public class SensorService {
    @Autowired
    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }
    public void save(Sensor sensor){
        try{
            findByName(sensor.getName());
        }
        catch(SensorNotFoundException e) {
            sensorRepository.save(sensor);
        }
    }
    public Sensor findByName(String name) {
        Optional<Sensor> foundSensor = sensorRepository.findByName(name);
        return foundSensor.orElseThrow(SensorNotFoundException::new);
    }

}
