package dev.anirban.stridesync.repo;

import dev.anirban.stridesync.entity.Water;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface WaterRepo extends JpaRepository<Water, Integer> {
    List<Water> findByDrankBy_Username(String username);

    List<Water> findByDrankBy_UsernameAndDrankAtBetween(String username, Timestamp start, Timestamp end);
}