package pl.agh.edu.master_diet.core.model.rest.diary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.agh.edu.master_diet.core.model.rest.diary.demand.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultipleRecentProductsInfo {

    public static MultipleRecentProductsInfo createEmpty() {
        return MultipleRecentProductsInfo.builder()
                .fatInfo(new FatInfo())
                .caloriesInfo(new CaloriesInfo())
                .proteinsInfo(new ProteinsInfo())
                .carbohydratesInfo(new CarbohydratesInfo())
                .build();
    }

    private FatInfo fatInfo;
    private ProteinsInfo proteinsInfo;
    private CarbohydratesInfo carbohydratesInfo;
    private CaloriesInfo caloriesInfo;

    public void updateValues(final SingleRecentProductInfo info) {
        fatInfo.updateSum(info.getFatEaten());
        caloriesInfo.updateSum(info.getCaloriesEaten());
        carbohydratesInfo.updateSum(info.getCarbohydratesEaten());
        proteinsInfo.updateSum(info.getProteinsEaten());
    }
}
