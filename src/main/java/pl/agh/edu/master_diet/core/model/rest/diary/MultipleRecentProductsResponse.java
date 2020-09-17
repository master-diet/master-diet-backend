package pl.agh.edu.master_diet.core.model.rest.diary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.agh.edu.master_diet.core.model.rest.diary.demand.DemandInfo;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultipleRecentProductsResponse {

    private List<SingleRecentProductInfo> recentProducts;
    private List<SimpleSummaryRestProductInfo> summaryList;
//    private MultipleRecentProductsInfo summarizedInfo;
    private DemandInfo demandInfo;
}
