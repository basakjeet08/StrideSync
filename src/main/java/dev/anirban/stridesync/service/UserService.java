package dev.anirban.stridesync.service;

import dev.anirban.stridesync.dto.request.AuthDto;
import dev.anirban.stridesync.entity.User;
import dev.anirban.stridesync.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    public User create(AuthDto user) {

        if (userRepo.findByUsername(user.getUsername()).isPresent())
            throw new RuntimeException("User already present !!");

        User newUser = User
                .builder()
                .name(user.getName())
                .username(user.getUsername())
                .password(encoder.encode(user.getPassword()))
                .avatar(user.getAvatar())
                .gender(user.getGender())
                .dateOfBirth(user.getDateOfBirth())
                .roles(User.UserRole.USER)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        return userRepo.save(newUser);
    }

    public User findByUsername(String username) {
        return userRepo
                .findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found !!"));
    }
}