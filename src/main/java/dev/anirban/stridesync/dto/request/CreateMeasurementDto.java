package dev.anirban.stridesync.dto.request;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMeasurementDto {
    private Integer height;
    private Integer weight;
    private Timestamp measuredDate;
}