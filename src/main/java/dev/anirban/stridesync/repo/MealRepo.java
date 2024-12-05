package dev.anirban.stridesync.repo;

import dev.anirban.stridesync.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface MealRepo extends JpaRepository<Meal, Integer> {

    List<Meal> findByEatenBy_Username(String username);

    List<Meal> findByEatenBy_UsernameAndDateBetween(String username, Timestamp start, Timestamp end);
}