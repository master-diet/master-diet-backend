package pl.agh.edu.master_diet.core.model.rest.diary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultipleRecentProductsResponse {

    private List<SingleRecentProductInfo> recentProducts;
    private MultipleRecentProductsInfo summarizedInfo;
}
