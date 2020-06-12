package pl.agh.edu.master_diet.service.converter;

import pl.agh.edu.master_diet.core.model.database.UserPlan;
import pl.agh.edu.master_diet.core.model.rest.userPlan.UserPlanResponse;

public interface ConversionService {

    UserPlanResponse convert(UserPlan userPlan);
    UserParameters convert(UserParametersRequest request);
}
