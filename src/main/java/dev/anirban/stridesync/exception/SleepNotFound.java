package dev.anirban.stridesync.exception;

public class SleepNotFound extends DataNotFound {
    public SleepNotFound(Integer id) {
        super("Sleep entry with ID : " + id + " is not found !!");
    }
}