package dev.anirban.stridesync.entity;

import dev.anirban.stridesync.dto.common.ActivityDto;
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
@Table(name = "ACTIVITY_DB")
public class Activity {


    public enum ActivityType {
        WALKING, RUNNING, CYCLING, OTHER
    }

    public enum Intensity {
        ONE, TWO, THREE, FOUR, FIVE
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "type", nullable = false)
    private ActivityType type;

    @Column(name = "start_time", nullable = false)
    private Timestamp startTime;

    @Column(name = "end_time", nullable = false)
    private Timestamp endTime;

    @Column(name = "intensity", nullable = false)
    private Intensity intensity;

    @Column(name = "note")
    private String note;

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
    @JoinColumn(name = "completed_by_id")
    private User completedBy;

    public ActivityDto toActivityDto() {
        return ActivityDto
                .builder()
                .id(id)
                .title(title)
                .type(type)
                .startTime(startTime)
                .endTime(endTime)
                .intensity(intensity)
                .note(note)
                .date(date)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}