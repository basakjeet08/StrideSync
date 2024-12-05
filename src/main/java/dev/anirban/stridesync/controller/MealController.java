package dev.anirban.stridesync.controller;

import dev.anirban.stridesync.constants.UrlConstants;
import dev.anirban.stridesync.dto.common.MealDto;
import dev.anirban.stridesync.dto.response.ResponseWrapper;
import dev.anirban.stridesync.entity.Meal;
import dev.anirban.stridesync.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MealController {

    private final MealService service;

    @PostMapping(UrlConstants.CREATE_MEAL)
    public ResponseWrapper<MealDto> create(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody MealDto mealDto
    ) {

        MealDto data = service
                .create(userDetails.getUsername(), mealDto)
                .toMealDto();

        return new ResponseWrapper<>(data);
    }

    @GetMapping(UrlConstants.FIND_MEAL_QUERY)
    public ResponseWrapper<List<MealDto>> findMeal(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(name = "start", required = false) String start,
            @RequestParam(name = "end", required = false) String end
    ) {

        List<Meal> mealList;

        if (start != null && end != null)
            mealList = service.findByEatenBy_UsernameAndDateBetween(userDetails.getUsername(), start, end);
        else
            mealList = service.findByEatenBy_Username(userDetails.getUsername());

        List<MealDto> data = mealList
                .stream()
                .map(Meal::toMealDto)
                .toList();

        return new ResponseWrapper<>(data);
    }

    @PutMapping(UrlConstants.PUT_MEAL_QUERY)
    public ResponseWrapper<MealDto> update(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody MealDto mealDto
    ) {
        MealDto data = service
                .update(userDetails.getUsername(), mealDto)
                .toMealDto();

        return new ResponseWrapper<>(data);
    }

    @DeleteMapping(UrlConstants.DELETE_MEAL_QUERY)
    public ResponseWrapper<Object> deleteById(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer id
    ) {
        service.deleteById(id, userDetails.getUsername());
        return new ResponseWrapper<>("Meal Entry Deleted Successfully");
    }
}