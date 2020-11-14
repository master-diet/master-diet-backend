package pl.agh.edu.master_diet.service.converter;

import org.springframework.stereotype.Component;
import pl.agh.edu.master_diet.core.model.database.UserWeight;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserWeightResponse;

@Component
public class UserWeightConverter implements GenericConverter<UserWeight, UserWeightResponse> {
    @Override
    public UserWeightResponse createFrom(UserWeight dto) {
        return UserWeightResponse.builder()
                .creationDate(dto.getCreationDate())
                .weight(dto.getWeight())
                .build();
    }
}
