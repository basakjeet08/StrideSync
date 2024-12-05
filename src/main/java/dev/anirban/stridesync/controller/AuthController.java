package dev.anirban.stridesync.controller;

import dev.anirban.stridesync.constants.UrlConstants;
import dev.anirban.stridesync.dto.request.AuthDto;
import dev.anirban.stridesync.dto.response.ResponseWrapper;
import dev.anirban.stridesync.dto.response.TokenWrapper;
import dev.anirban.stridesync.dto.response.UserDto;
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
    public ResponseWrapper<UserDto> register(@RequestBody AuthDto user) {
        UserDto data = service.register(user).toUserDto();
        return new ResponseWrapper<>(data);
    }

    @PostMapping(UrlConstants.LOGIN_USER)
    public ResponseWrapper<TokenWrapper> login(@RequestBody AuthDto user) {
        TokenWrapper data = service.login(user);
        return new ResponseWrapper<>(null, data);
    }
}