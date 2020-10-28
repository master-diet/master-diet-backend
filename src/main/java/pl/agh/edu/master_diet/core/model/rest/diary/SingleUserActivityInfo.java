package pl.agh.edu.master_diet.core.model.rest.diary;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import pl.agh.edu.master_diet.core.model.shared.MealType;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleUserActivityInfo {


    private final MealType mealType = MealType.ACTIVITY;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime activityTime;

    private String activityName;
    private Integer caloriesBurned;
    private Long userActivityId;
}
