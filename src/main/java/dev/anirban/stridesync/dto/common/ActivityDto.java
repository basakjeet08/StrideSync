package dev.anirban.stridesync.dto.common;


import dev.anirban.stridesync.entity.Activity;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDto {
    private Integer id;
    private String title;
    private Activity.ActivityType type;
    private Timestamp startTime;
    private Timestamp endTime;
    private Activity.Intensity intensity;
    private String note;
    private Timestamp date;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}