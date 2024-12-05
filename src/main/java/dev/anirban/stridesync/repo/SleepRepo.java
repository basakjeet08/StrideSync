package dev.anirban.stridesync.repo;

import dev.anirban.stridesync.entity.Sleep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface SleepRepo extends JpaRepository<Sleep, Integer> {
    List<Sleep> findBySleptBy_Username(String username);

    List<Sleep> findBySleptBy_UsernameAndDateBetween(String username, Timestamp start, Timestamp end);
}