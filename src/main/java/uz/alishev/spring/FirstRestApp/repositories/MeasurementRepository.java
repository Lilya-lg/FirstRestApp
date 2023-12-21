package uz.alishev.spring.FirstRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.alishev.spring.FirstRestApp.models.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {

}
