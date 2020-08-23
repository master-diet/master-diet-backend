package pl.agh.edu.master_diet.core.model.rest.diary.demand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemandInfo {

    private FatInfo fat;
    private CaloriesInfo calories;
    private ProteinsInfo proteins;
    private CarbohydratesInfo carbohydrates;
    private DemandStatusEnum overallStatus;
}
