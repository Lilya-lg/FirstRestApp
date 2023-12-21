package uz.alishev.spring.FirstRestApp.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.alishev.spring.FirstRestApp.models.Measurement;
import uz.alishev.spring.FirstRestApp.repositories.MeasurementRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MeasurementService {
    @Autowired
    private final MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }
    public void save(Measurement measurement){
       // measurementDTO.setCreated_at(new Date());
        measurementRepository.save(measurement);
    }
    public List<Measurement> findAll(){
       return measurementRepository.findAll();
    }
    public List<Measurement> getRainyDays(){
        List<Measurement> rainyDays = new ArrayList<>();
        List<Measurement> measurementDTOList = findAll();
        for(Measurement meas:measurementDTOList){
            if(meas.isRaining()){
                rainyDays.add(meas);
            }
        }
        return rainyDays;
    }
    public void enrich(Measurement measurement){
        measurement.setCreated_at(new Date());
    }
}
