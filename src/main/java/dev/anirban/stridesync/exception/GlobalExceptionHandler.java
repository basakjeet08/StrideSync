package dev.anirban.stridesync.exception;

import dev.anirban.stridesync.dto.response.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(Exception exception) {
        ResponseWrapper<Object> response = new ResponseWrapper<>(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataNotFound.class)
    public ResponseEntity<ResponseWrapper<Object>> handleDataNotFoundException(Exception exception) {
        ResponseWrapper<Object> response = new ResponseWrapper<>(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<ResponseWrapper<Object>> handleUserAlreadyExistsException(Exception exception) {
        ResponseWrapper<Object> response = new ResponseWrapper<>(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}