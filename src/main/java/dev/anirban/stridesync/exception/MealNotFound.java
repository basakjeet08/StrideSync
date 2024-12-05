package dev.anirban.stridesync.exception;

public class MealNotFound extends DataNotFound {
    public MealNotFound(Integer id) {
        super("Meal Entry with ID : " + id + " is not found !!");
    }
}