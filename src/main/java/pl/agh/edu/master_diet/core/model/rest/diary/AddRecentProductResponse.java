package pl.agh.edu.master_diet.core.model.rest.diary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.agh.edu.master_diet.core.model.shared.MealType;
import pl.agh.edu.master_diet.core.model.shared.Unit;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddRecentProductResponse {

    private MealType mealType;
    private Float amount;
    private Float portion;
    private Unit portionUnit;
    private Long userId;
    private Long productId;
    private boolean success;
}
