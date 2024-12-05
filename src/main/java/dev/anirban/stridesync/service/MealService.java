package dev.anirban.stridesync.service;


import dev.anirban.stridesync.dto.common.MealDto;
import dev.anirban.stridesync.entity.Meal;
import dev.anirban.stridesync.entity.User;
import dev.anirban.stridesync.exception.MealNotFound;
import dev.anirban.stridesync.exception.UserNotAuthenticated;
import dev.anirban.stridesync.repo.MealRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepo mealRepo;
    private final UserService userService;

    public Meal create(String username, MealDto mealDto) {

        // Check user
        User savedUser = userService.findByUsername(username);

        Meal newMeal = Meal
                .builder()
                .type(mealDto.getType() != null ? mealDto.getType() : Meal.MealType.NOT_SPECIFIED)
                .date(mealDto.getDate())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        savedUser.addMealHistory(newMeal);

        return mealRepo.save(newMeal);
    }

    public Meal findById(Integer id) {
        return mealRepo
                .findById(id)
                .orElseThrow(() -> new MealNotFound(id));
    }

    public List<Meal> findByEatenBy_Username(String username) {
        return mealRepo.findByEatenBy_Username(username);
    }

    public List<Meal> findByEatenBy_UsernameAndDateBetween(
            String username,
            String start,
            String end
    ) {

        // Convert String to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startLocalDate = LocalDate.parse(start, formatter);
        LocalDate endLocalDate = LocalDate.parse(end, formatter);

        Timestamp startTime = Timestamp.valueOf(startLocalDate.atStartOfDay());
        Timestamp endTime = Timestamp.valueOf(endLocalDate.atTime(23, 59, 59));
        return mealRepo.findByEatenBy_UsernameAndDateBetween(username, startTime, endTime);
    }

    public Meal update(String username, MealDto mealDto) {

        // Fetch Meal
        Meal savedMeal = findById(mealDto.getId());

        // Verify that the username is valid
        if (!savedMeal.getEatenBy().getUsername().equals(username))
            throw new UserNotAuthenticated();

        // Update
        if (mealDto.getType() != null)
            savedMeal.setType(mealDto.getType());

        if (mealDto.getDate() != null)
            savedMeal.setDate(mealDto.getDate());


        savedMeal.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        return mealRepo.save(savedMeal);
    }

    public void deleteById(Integer id, String username) {
        Meal savedMeal = findById(id);

        if (!savedMeal.getEatenBy().getUsername().equals(username))
            throw new UserNotAuthenticated();

        mealRepo.deleteById(id);
    }
}