package dev.anirban.stridesync.exception;

public class UserNotFound extends DataNotFound {
    public UserNotFound(Integer id) {
        super("User with ID : " + id + " is not found !!");
    }

    public UserNotFound(String username) {
        super("User with Username : " + username + " is not found !!");
    }
}