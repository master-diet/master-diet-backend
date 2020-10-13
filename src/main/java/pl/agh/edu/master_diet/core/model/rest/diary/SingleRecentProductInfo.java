package pl.agh.edu.master_diet.core.model.rest.diary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import pl.agh.edu.master_diet.core.model.shared.MealType;
import pl.agh.edu.master_diet.core.model.shared.Unit;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleRecentProductInfo {

    private MealType mealType;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime mealTime;

    private Float amount;
    private Float portion;
    private Unit mealUnit;
    private String productName;
    private Float caloriesEaten;
    private Float proteinsEaten;
    private Float fatEaten;
    private Float carbohydratesEaten;
    private Long recentProductId;


}
