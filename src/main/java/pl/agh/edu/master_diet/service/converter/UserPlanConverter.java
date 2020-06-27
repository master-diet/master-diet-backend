package pl.agh.edu.master_diet.service.converter;

import org.springframework.stereotype.Component;
import pl.agh.edu.master_diet.core.model.database.UserPlan;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserPlanResponse;

@Component
public final class UserPlanConverter
        implements GenericConverter<UserPlan, UserPlanResponse> {

    @Override
    public UserPlanResponse createFrom(final UserPlan dto) {
        return UserPlanResponse.builder()
                .userId(dto.getId())
                .calories(dto.getCalories())
                .carbohydrates(dto.getCarbohydrates())
                .fat(dto.getFat())
                .proteins(dto.getProteins())
                .build();
    }
}
