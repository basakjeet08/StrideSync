package dev.anirban.stridesync.entity;


import dev.anirban.stridesync.dto.common.MeasurementDto;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MEASUREMENT_DB")
public class Measurement {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "height", nullable = false)
    private Integer height;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "measured_date", nullable = false)
    private Timestamp measuredDate;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @ManyToOne(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "measured_by_id")
    private User measuredBy;

    public MeasurementDto toMeasurementDto() {
        return MeasurementDto
                .builder()
                .id(id)
                .height(height)
                .weight(weight)
                .measuredDate(measuredDate)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}