package pl.agh.edu.master_diet.core.model.rest.user_plan;

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
    private Integer proteins;

    @NonNull
    private Integer fat;

    @NonNull
    private Integer carbohydrates;
}
