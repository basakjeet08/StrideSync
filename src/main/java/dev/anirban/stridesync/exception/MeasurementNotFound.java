package dev.anirban.stridesync.exception;

public class MeasurementNotFound extends DataNotFound {
    public MeasurementNotFound(Integer id) {
        super("Measurement with ID : " + id + " is not found !!");
    }
}