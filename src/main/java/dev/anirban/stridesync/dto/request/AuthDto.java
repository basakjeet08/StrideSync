package dev.anirban.stridesync.dto.request;

import dev.anirban.stridesync.entity.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {
    private String name;
    private String username;
    private String password;
    private String avatar;
    private User.Gender gender;
    private Date dateOfBirth;
}