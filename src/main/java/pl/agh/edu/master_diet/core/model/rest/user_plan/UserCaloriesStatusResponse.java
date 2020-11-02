package pl.agh.edu.master_diet.core.model.rest.user_plan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCaloriesStatusResponse {

    @NotNull
    private Integer caloriesConsumed;

    @NotNull
    private Integer dailyCaloricDemand;
}
