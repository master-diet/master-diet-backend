package pl.agh.edu.master_diet.core.model.rest.product_browser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseProductInfo {

    @NonNull
    private String name;

    @NonNull
    private Long defaultValue;

    @NonNull
    private Long calories;
}
