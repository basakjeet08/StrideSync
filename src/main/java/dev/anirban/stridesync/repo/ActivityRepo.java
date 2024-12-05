package dev.anirban.stridesync.repo;

import dev.anirban.stridesync.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface ActivityRepo extends JpaRepository<Activity, Integer> {
    List<Activity> findByCompletedBy_Username(String username);

    List<Activity> findByCompletedBy_UsernameAndDateBetween(String username, Timestamp start, Timestamp end);
}