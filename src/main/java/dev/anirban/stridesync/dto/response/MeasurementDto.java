package dev.anirban.stridesync.dto.response;

import lombok.*;

import java.sql.Timestamp;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDto {
    private Integer id;
    private Integer height;
    private Integer weight;
    private Timestamp measuredDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}