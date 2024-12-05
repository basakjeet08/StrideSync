package dev.anirban.stridesync.entity;

import dev.anirban.stridesync.dto.common.MealDto;
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
@Table(name = "MEAL_DB")
public class Meal {

    public enum MealType {
        BREAKFAST,
        LUNCH,
        SNACK,
        DINNER,
        NOT_SPECIFIED
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "type", nullable = false)
    private MealType type;

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
    @JoinColumn(name = "eaten_by_id")
    private User eatenBy;

    public MealDto toMealDto() {
        return MealDto
                .builder()
                .id(id)
                .type(type)
                .date(date)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}