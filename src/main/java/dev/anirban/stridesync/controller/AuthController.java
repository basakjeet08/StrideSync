package dev.anirban.stridesync.controller;

import dev.anirban.stridesync.constants.UrlConstants;
import dev.anirban.stridesync.entity.User;
import dev.anirban.stridesync.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping(UrlConstants.REGISTER_USER)
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping(UrlConstants.LOGIN_USER)
    public String login(@RequestBody User user) {
        return service.login(user);
    }
}