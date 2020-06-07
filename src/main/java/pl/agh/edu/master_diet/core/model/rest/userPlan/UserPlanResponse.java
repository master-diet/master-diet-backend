package pl.agh.edu.master_diet.core.model.rest.userPlan;

import lombok.Builder;
import lombok.NonNull;

@Builder
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
