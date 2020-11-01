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
public class UserCaloriesStatusResponse {

    @NonNull
    private Integer caloriesConsumed;

    @NonNull
    private Integer dailyCaloricDemand;
}
