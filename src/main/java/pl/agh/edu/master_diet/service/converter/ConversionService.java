package pl.agh.edu.master_diet.service.converter;

import pl.agh.edu.master_diet.core.model.database.UserPlan;
import pl.agh.edu.master_diet.core.model.rest.diary.AddRecentProductRequest;
import pl.agh.edu.master_diet.core.model.rest.userPlan.UserParametersRequest;
import pl.agh.edu.master_diet.core.model.rest.userPlan.UserPlanResponse;
import pl.agh.edu.master_diet.core.model.shared.RecentProductParameters;
import pl.agh.edu.master_diet.core.model.shared.UserParameters;

public interface ConversionService {
    UserParameters convert(UserParametersRequest request);

    UserPlanResponse convert(UserPlan request);

    RecentProductParameters convert(AddRecentProductRequest request);
}
