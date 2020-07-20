package pl.agh.edu.master_diet.core.model.rest.browser.product;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecentProductsResponse {

    @NonNull
    private List<BaseProductInfo> products;

    @NonNull
    private Integer maximumPageNumber;
}
