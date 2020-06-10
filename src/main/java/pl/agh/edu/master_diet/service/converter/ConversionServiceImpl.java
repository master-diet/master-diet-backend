package pl.agh.edu.master_diet.service.converter;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.UserPlan;
import pl.agh.edu.master_diet.core.model.rest.userPlan.UserPlanResponse;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public final class ConversionServiceImpl implements ConversionService {

    private final UserPlanConverter userPlanConverter;

    @Override
    public UserPlanResponse convert(final UserPlan userPlan) {
        return userPlanConverter.createFrom(userPlan);
    }
}
