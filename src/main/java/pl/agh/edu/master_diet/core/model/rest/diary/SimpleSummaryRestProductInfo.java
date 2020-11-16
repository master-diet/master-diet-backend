package pl.agh.edu.master_diet.core.model.rest.diary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private int difference;

    public static List<SimpleSummaryRestProductInfo> fromNutrientInfoList(final List<AbstractNutrientInfo> info) {
        return info.stream()
                .map(SimpleSummaryRestProductInfo::fromNutrientInfo)
                .collect(toList());
    }

    private static SimpleSummaryRestProductInfo fromNutrientInfo(final AbstractNutrientInfo nutrientInfo) {
        return SimpleSummaryRestProductInfo.builder()
                .sum(roundFloatNumber(nutrientInfo.getSum()))
                .description(nutrientInfo.getDescription())
                .difference(nutrientInfo.getDifference())
                .build();
    }
}
