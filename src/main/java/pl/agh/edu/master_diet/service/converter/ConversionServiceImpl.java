package pl.agh.edu.master_diet.service.converter;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.*;
import pl.agh.edu.master_diet.core.model.rest.AchievementsResponse;
import pl.agh.edu.master_diet.core.model.rest.browser.activity.BaseActivityInfo;
import pl.agh.edu.master_diet.core.model.rest.browser.product.BaseProductInfo;
import pl.agh.edu.master_diet.core.model.rest.calculator.bmi.CalculateBMIRequest;
import pl.agh.edu.master_diet.core.model.rest.diary.AddRecentProductRequest;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserParametersRequest;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserPlanResponse;
import pl.agh.edu.master_diet.core.model.shared.BMIParameters;
import pl.agh.edu.master_diet.core.model.shared.RecentProductParameters;
import pl.agh.edu.master_diet.core.model.shared.UserParameters;
import pl.agh.edu.master_diet.service.AchievementConverter;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public final class ConversionServiceImpl implements ConversionService {

    private final UserParametersConverter userParametersConverter;
    private final UserPlanConverter userPlanConverter;
    private final RecentProductConverter recentProductConverter;
    private final BMIConverter userBMIConverter;
    private final ProductConverter productConverter;
    private final ActivityConverter activityConverter;
    private final UserActivityConverter userActivityConverter;
    private final UserAchievementConverter userAchievementConverter;
    private final AchievementConverter achievementConverter;

    @Override
    public UserParameters convert(UserParametersRequest request) {
        return userParametersConverter.createFrom(request);
    }

    @Override
    public UserPlanResponse convert(UserPlan userPlan) {
        return userPlanConverter.createFrom(userPlan);
    }

    @Override
    public BMIParameters convert(CalculateBMIRequest request) {
        return userBMIConverter.createFrom(request);
    }

    @Override
    public BaseProductInfo convert(Product product) {
        return productConverter.createFrom(product);
    }

    @Override
    public RecentProductParameters convert(AddRecentProductRequest request) {
        return recentProductConverter.createFrom(request);
    }

    @Override
    public BaseProductInfo convert(RecentProduct recentProduct) {
        return recentProductConverter.createFrom(recentProduct);
    }

    @Override
    public RecentProduct convert(RecentProductParameters parameters, Product product, User user) {
        return recentProductConverter.createFrom(parameters, product, user);
    }

    @Override
    public BaseActivityInfo convert(Activity activity) {
        return activityConverter.createFrom(activity);
    }

    @Override
    public BaseActivityInfo convert(UserActivity userActivity) {
        return userActivityConverter.createFrom(userActivity);
    }

    @Override
    public AchievementsResponse convert(UserAchievement userAchievement) {
        return userAchievementConverter.createFrom(userAchievement);
    }

    @Override
    public AchievementsResponse convert(Achievement achievement) {
        return achievementConverter.createFrom(achievement);
    }
}
