package dev.anirban.stridesync.entity;


import dev.anirban.stridesync.dto.common.SleepDto;
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
@Table(name = "SLEEP_DB")
public class Sleep {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "went_to_bed", nullable = false)
    private Timestamp wentToBed;

    @Column(name = "woke_up", nullable = false)
    private Timestamp wokeUp;

    @Column(name = "date", nullable = false)
    private Timestamp date;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @ManyToOne(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "slept_by_id")
    private User sleptBy;

    public SleepDto toSleepDto() {
        return SleepDto
                .builder()
                .id(id)
                .wentToBed(wentToBed)
                .wokeUp(wokeUp)
                .date(date)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}