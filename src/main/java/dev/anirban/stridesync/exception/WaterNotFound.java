package dev.anirban.stridesync.exception;

public class WaterNotFound extends DataNotFound {
    public WaterNotFound(Integer id) {
        super("The water entry with ID : " + id + " is not found !!");
    }
}