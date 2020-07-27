package pl.agh.edu.master_diet.service.converter;

import org.springframework.stereotype.Component;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserParametersRequest;
import pl.agh.edu.master_diet.core.model.shared.UserParameters;

@Component
public final class UserParametersConverter
        implements GenericConverter<UserParametersRequest, UserParameters> {

    @Override
    public UserParameters createFrom(UserParametersRequest dto) {
        return UserParameters.builder()
                .userId(dto.getUserId())
                .birthDate(dto.getBirthDate())
                .gender(dto.getGender())
                .activityLevel(dto.getActivityLevel())
                .height(dto.getHeight())
                .weight(dto.getWeight())
                .goal(dto.getGoal())
                .fatPreferencesPercentage(dto.getFatPreferencesPercentage())
                .build();
    }
}
