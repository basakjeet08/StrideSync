package dev.anirban.stridesync.dto.common;


import dev.anirban.stridesync.entity.Meal;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MealDto {
    private Integer id;
    private Meal.MealType type;
    private Timestamp date;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}