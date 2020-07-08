package pl.agh.edu.master_diet.service.converter;

import pl.agh.edu.master_diet.core.model.database.Product;
import pl.agh.edu.master_diet.core.model.database.RecentProduct;
import pl.agh.edu.master_diet.core.model.database.UserPlan;
import pl.agh.edu.master_diet.core.model.rest.product_browser.BaseProductInfo;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserParametersRequest;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserPlanResponse;
import pl.agh.edu.master_diet.core.model.shared.UserParameters;

public interface ConversionService {

    UserParameters convert(UserParametersRequest request);

    UserPlanResponse convert(UserPlan request);

    BaseProductInfo convert(Product product);

    BaseProductInfo convert(RecentProduct recentProduct);
}
