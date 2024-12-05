package dev.anirban.stridesync.exception;

public class UserNotAuthenticated extends RuntimeException {
    public UserNotAuthenticated() {
        super("User is not authenticated to perform this operation !!");
    }
}