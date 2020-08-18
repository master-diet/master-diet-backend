package pl.agh.edu.master_diet.core.model.rest.diary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultipleRecentProductsInfo {

    private float caloriesSum;
    private float proteinsSum;
    private float fatSum;
    private float carbohydratesSum;

    public void updateValues(final SingleRecentProductInfo info) {
        caloriesSum += info.getCaloriesEaten();
        fatSum += info.getFatEaten();
        carbohydratesSum += info.getCarbohydratesEaten();
        proteinsSum += info.getProteinsEaten();
    }
}
