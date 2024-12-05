package dev.anirban.stridesync.exception;

public class UserNotFound extends DataNotFound {
    public UserNotFound(String username) {
        super("User with Username : " + username + " is not found !!");
    }
}