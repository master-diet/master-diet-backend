package pl.agh.edu.master_diet.core.model.rest.userPlan;

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
public class UserPlanResponse {

    @NonNull
    private Long userId;

    @NonNull
    private Integer calories;

    @NonNull
    private Float proteins;

    @NonNull
    private Float fat;

    @NonNull
    private Float carbohydrates;
}
