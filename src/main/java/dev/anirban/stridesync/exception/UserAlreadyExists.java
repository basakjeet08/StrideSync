package dev.anirban.stridesync.exception;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists(String username) {
        super("User with Username : " + username + " is already present !!");
    }
}