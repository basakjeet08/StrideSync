package dev.anirban.stridesync.dto.common;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SleepDto {
    private Integer id;
    private Timestamp wentToBed;
    private Timestamp wokeUp;
    private Timestamp date;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}