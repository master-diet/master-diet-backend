package pl.agh.edu.master_diet.core.model.rest.diary;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.agh.edu.master_diet.core.model.shared.MealType;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddRecentProductRequest {

    private MealType mealType;
    private Float amount;
    private Float portion;
    private Long userId;
    private Long productId;
}
