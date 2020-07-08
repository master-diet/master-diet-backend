package pl.agh.edu.master_diet.service.converter;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.UserPlan;
import pl.agh.edu.master_diet.core.model.rest.diary.AddRecentProductRequest;
import pl.agh.edu.master_diet.core.model.rest.calculator.bmi.CalculateBMIRequest;
import pl.agh.edu.master_diet.core.model.rest.userPlan.UserParametersRequest;
import pl.agh.edu.master_diet.core.model.rest.userPlan.UserPlanResponse;
import pl.agh.edu.master_diet.core.model.shared.RecentProductParameters;
import pl.agh.edu.master_diet.core.model.shared.BMIParameters;
import pl.agh.edu.master_diet.core.model.shared.UserParameters;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public final class ConversionServiceImpl implements ConversionService {

    private final UserParametersConverter userParametersConverter;
    private final UserPlanConverter userPlanConverter;
    private final RecentProductConverter recentProductConverter;
    private final BMIConverter userBMIConverter;

    @Override
    public UserParameters convert(UserParametersRequest request) {
        return userParametersConverter.createFrom(request);
    }

    @Override
    public UserPlanResponse convert(UserPlan userPlan) {
        return userPlanConverter.createFrom(userPlan);
    }

    @Override
    public RecentProductParameters convert(AddRecentProductRequest request) {
        return recentProductConverter.createFrom(request);
    }

    @Override
    public BMIParameters convert(CalculateBMIRequest request) {
        return userBMIConverter.createFrom(request);
    }
}
