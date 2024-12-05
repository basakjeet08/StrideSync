package dev.anirban.stridesync.repo;

import dev.anirban.stridesync.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MeasurementRepo extends JpaRepository<Measurement, Integer> {

    List<Measurement> findByMeasuredBy_Username(String username);
}