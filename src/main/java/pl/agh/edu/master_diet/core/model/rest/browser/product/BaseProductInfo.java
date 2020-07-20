package pl.agh.edu.master_diet.core.model.rest.browser.product;

import lombok.*;
import pl.agh.edu.master_diet.core.model.shared.Unit;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseProductInfo {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Long defaultValue;

    @NonNull
    private Unit unit;

    @NonNull
    private Long calories;
}
