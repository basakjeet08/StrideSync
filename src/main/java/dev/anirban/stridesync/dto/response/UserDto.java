package dev.anirban.stridesync.dto.response;

import dev.anirban.stridesync.entity.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String name;
    private String username;
    private String avatar;
    private User.Gender gender;
    private Date dateOfBirth;
}