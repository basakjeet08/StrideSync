package dev.anirban.stridesync.controller;


import dev.anirban.stridesync.constants.UrlConstants;
import dev.anirban.stridesync.dto.response.ResponseWrapper;
import dev.anirban.stridesync.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @DeleteMapping(UrlConstants.DELETE_USER)
    public ResponseWrapper<Object> deleteByUsername(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        service.deleteByUsername(userDetails.getUsername());
        return new ResponseWrapper<>("User Deleted Successfully !!");
    }
}