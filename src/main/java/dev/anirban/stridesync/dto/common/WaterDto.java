package dev.anirban.stridesync.dto.common;


import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WaterDto {
    private Integer id;
    private Integer volume;
    private Timestamp drankAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}