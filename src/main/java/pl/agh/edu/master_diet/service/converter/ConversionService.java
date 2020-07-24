package pl.agh.edu.master_diet.service.converter;

import pl.agh.edu.master_diet.core.model.database.*;
import pl.agh.edu.master_diet.core.model.rest.AchievementsResponse;
import pl.agh.edu.master_diet.core.model.rest.browser.activity.BaseActivityInfo;
import pl.agh.edu.master_diet.core.model.rest.browser.product.BaseProductInfo;
import pl.agh.edu.master_diet.core.model.rest.calculator.bmi.CalculateBMIRequest;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserParametersRequest;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserPlanResponse;
import pl.agh.edu.master_diet.core.model.shared.BMIParameters;
import pl.agh.edu.master_diet.core.model.shared.UserParameters;

public interface ConversionService {

    UserParameters convert(UserParametersRequest request);

    UserPlanResponse convert(UserPlan request);

    BMIParameters convert(CalculateBMIRequest request);

    BaseProductInfo convert(Product product);

    BaseProductInfo convert(RecentProduct recentProduct);

    BaseActivityInfo convert(Activity activity);

    BaseActivityInfo convert(UserActivity userActivity);

    AchievementsResponse convert(UserAchievement userAchievement);

    AchievementsResponse convert(Achievement achievement);
}
