package pl.agh.edu.master_diet.service.converter;

import org.springframework.stereotype.Component;
import pl.agh.edu.master_diet.core.model.rest.caloricDemand.UserParametersRequest;
import pl.agh.edu.master_diet.core.model.shared.UserParameters;

@Component
public final class UserParametersConverter implements GenericConverter<UserParametersRequest, UserParameters> {
    @Override
    public UserParameters createFrom(UserParametersRequest dto) {
        return UserParameters.builder()
                .age(dto.getAge())
                .gender(dto.getGender())
                .activityLevel(dto.getActivityLevel())
                .height(dto.getHeight())
                .weight(dto.getWeight())
                .build();

    }
}
