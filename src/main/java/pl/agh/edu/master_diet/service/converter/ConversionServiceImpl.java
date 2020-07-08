package pl.agh.edu.master_diet.service.converter;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.Product;
import pl.agh.edu.master_diet.core.model.database.RecentProduct;
import pl.agh.edu.master_diet.core.model.database.UserPlan;
import pl.agh.edu.master_diet.core.model.rest.product_browser.BaseProductInfo;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserParametersRequest;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserPlanResponse;
import pl.agh.edu.master_diet.core.model.shared.UserParameters;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public final class ConversionServiceImpl implements ConversionService {

    private final UserParametersConverter userParametersConverter;
    private final UserPlanConverter userPlanConverter;
    private final ProductConverter productConverter;
    private final RecentProductConverter recentProductConverter;

    @Override
    public UserParameters convert(UserParametersRequest request) {
        return userParametersConverter.createFrom(request);
    }

    @Override
    public UserPlanResponse convert(UserPlan userPlan) {
        return userPlanConverter.createFrom(userPlan);
    }

    @Override
    public BaseProductInfo convert(Product product) {
        return productConverter.createFrom(product);
    }

    @Override
    public BaseProductInfo convert(RecentProduct recentProduct) {
        return recentProductConverter.createFrom(recentProduct);
    }
}
