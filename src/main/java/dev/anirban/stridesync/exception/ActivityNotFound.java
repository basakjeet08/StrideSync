package dev.anirban.stridesync.exception;

public class ActivityNotFound extends DataNotFound {
    public ActivityNotFound(Integer id) {
        super("Activity Entry with the ID : " + id + " is not found !!");
    }
}