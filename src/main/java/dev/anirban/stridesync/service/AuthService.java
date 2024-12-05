package dev.anirban.stridesync.service;


import dev.anirban.stridesync.entity.User;
import dev.anirban.stridesync.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public User register(User user) {
        return userService.create(user);
    }

    public String login(User librarianDto) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        librarianDto.getUsername(), librarianDto.getPassword()
                )
        );

        User savedUser = userService.findByUsername(librarianDto.getUsername());

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .builder()
                .username(savedUser.getUsername())
                .password(savedUser.getPassword())
                .roles(savedUser.getRoles().name())
                .build();

        return jwtService.generateToken(userDetails);
    }
}