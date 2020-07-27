package pl.agh.edu.master_diet.core.model.shared;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecentProductParameters {

    private MealType mealType;
    private LocalDateTime mealTime;
    private Float amount;
    private Float portion;
    private Unit portionUnit;
    private Long userId;
    private Long productId;
}
