package dev.anirban.stridesync.entity;

import dev.anirban.stridesync.dto.common.WaterDto;
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
@Table(name = "WATER_DB")
public class Water {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "volume", nullable = false)
    private Integer volume;

    @Column(name = "drank_at", nullable = false)
    private Timestamp drankAt;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @ManyToOne(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "drank_by_id")
    private User drankBy;

    public WaterDto toWaterDto() {
        return WaterDto
                .builder()
                .id(id)
                .volume(volume)
                .drankAt(drankAt)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}