package pl.agh.edu.master_diet.core.model.rest.diary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.agh.edu.master_diet.core.model.database.UserPlan;
import pl.agh.edu.master_diet.core.model.rest.diary.demand.AbstractNutrientInfo;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static pl.agh.edu.master_diet.util.CommonFormatters.roundFloatNumber;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleSummaryRestProductInfo {

    private String description;
    private float sum;
    private int progress;

    public static List<SimpleSummaryRestProductInfo> fromNutrientInfoList(final UserPlan userPlan,
                                                                          final List<AbstractNutrientInfo> info) {
        return info.stream()
                .map(e -> fromNutrientInfo(userPlan, e))
                .collect(toList());
    }

    private static SimpleSummaryRestProductInfo fromNutrientInfo(final UserPlan userPlan,
                                                                 final AbstractNutrientInfo nutrientInfo) {
        return SimpleSummaryRestProductInfo.builder()
                .progress(getProgressForInfo(userPlan, nutrientInfo))
                .sum(roundFloatNumber(nutrientInfo.getSum()))
                .description(nutrientInfo.getDescription())
                .build();
    }

    private static int getProgressForInfo(final UserPlan userPlan,
                                          final AbstractNutrientInfo nutrientInfo) {
        switch (nutrientInfo.getIdentifier()) {
            case "calories":
                return (int) (nutrientInfo.getSum() * 100 / userPlan.getCalories());
            case "carbohydrates":
                return (int) (nutrientInfo.getSum() * 100 / userPlan.getCarbohydrates());
            case "fat":
                return (int) (nutrientInfo.getSum() * 100 / userPlan.getFat());
            case "proteins":
                return (int) (nutrientInfo.getSum() * 100 / userPlan.getProteins());
            default:
                return 0;
        }
    }
}
