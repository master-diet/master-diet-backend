package pl.agh.edu.master_diet.service.converter;

import org.springframework.stereotype.Component;
import pl.agh.edu.master_diet.core.model.database.Product;
import pl.agh.edu.master_diet.core.model.database.RecentProduct;
import pl.agh.edu.master_diet.core.model.database.User;
import pl.agh.edu.master_diet.core.model.rest.diary.AddRecentProductRequest;
import pl.agh.edu.master_diet.core.model.rest.product_browser.BaseProductInfo;
import pl.agh.edu.master_diet.core.model.shared.RecentProductParameters;

@Component
public final class RecentProductConverter
        implements GenericConverter<RecentProduct, BaseProductInfo> {

    private static final double GRAMS_PER_CALORIES_VALUE = 100.0;

    @Override
    public BaseProductInfo createFrom(final RecentProduct dto) {
        Product product = dto.getProduct();
        return BaseProductInfo.builder()
                .id(product.getId())
                .name(product.getName())
                .calories((long) (product.getCalories() / (GRAMS_PER_CALORIES_VALUE / product.getDefaultValue())))
                .defaultValue(product.getDefaultValue().longValue())
                .unit(product.getUnit())
                .build();
    }

    public RecentProductParameters createFrom(final AddRecentProductRequest request) {
        return RecentProductParameters.builder()
                .amount(request.getAmount())
                .mealType(request.getMealType())
                .mealTime(request.getMealTime())
                .portion(request.getPortion())
                .portionUnit(request.getPortionUnit())
                .productId(request.getProductId())
                .build();
    }

    public RecentProduct createFrom(final RecentProductParameters parameters,
                                    final Product product,
                                    final User user) {
        return RecentProduct.builder()
                .amount(parameters.getAmount())
                .mealType(parameters.getMealType())
                .mealTime(parameters.getMealTime())
                .portion(parameters.getPortion())
                .portionUnit(parameters.getPortionUnit())
                .product(product)
                .user(user)
                .build();
    }

}
