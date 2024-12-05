package dev.anirban.stridesync.service;


import dev.anirban.stridesync.dto.request.AuthDto;
import dev.anirban.stridesync.dto.response.TokenWrapper;
import dev.anirban.stridesync.entity.User;
import dev.anirban.stridesync.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public User register(AuthDto user) {
        return userService.create(user);
    }

    public TokenWrapper login(AuthDto user) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(), user.getPassword()
                )
        );

        User savedUser = userService.findByUsername(user.getUsername());
        return new TokenWrapper(jwtService.generateToken(savedUser));
    }
}