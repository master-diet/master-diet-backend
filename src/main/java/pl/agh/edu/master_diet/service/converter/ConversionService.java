package pl.agh.edu.master_diet.service.converter;

import pl.agh.edu.master_diet.core.model.database.*;
import pl.agh.edu.master_diet.core.model.rest.AchievementsResponse;
import pl.agh.edu.master_diet.core.model.rest.browser.activity.BaseActivityInfo;
import pl.agh.edu.master_diet.core.model.rest.browser.product.BaseProductInfo;
import pl.agh.edu.master_diet.core.model.rest.diary.AddRecentProductRequest;
import pl.agh.edu.master_diet.core.model.rest.diary.AddUserActivityRequest;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserParametersRequest;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserPlanResponse;
import pl.agh.edu.master_diet.core.model.shared.RecentProductParameters;
import pl.agh.edu.master_diet.core.model.shared.UserActivityParameters;
import pl.agh.edu.master_diet.core.model.shared.UserParameters;

public interface ConversionService {

    UserParameters convert(UserParametersRequest request);

    UserPlanResponse convert(UserPlan request);

    RecentProductParameters convert(AddRecentProductRequest request);

    UserActivityParameters convert(AddUserActivityRequest request);

    BaseProductInfo convert(Product product);

    BaseProductInfo convert(RecentProduct recentProduct);

    RecentProduct convert(RecentProductParameters parameters, Product product, User user);

    UserActivity convert(UserActivityParameters parameters, Activity activity, User user);

    BaseActivityInfo convert(Activity activity);

    BaseActivityInfo convert(UserActivity userActivity);

    AchievementsResponse convert(UserAchievement userAchievement);

    AchievementsResponse convert(Achievement achievement);
}
