package pl.agh.edu.master_diet.service.converter;

import org.springframework.stereotype.Component;
import pl.agh.edu.master_diet.core.model.database.Product;
import pl.agh.edu.master_diet.core.model.database.RecentProduct;
import pl.agh.edu.master_diet.core.model.rest.browser.product.BaseProductInfo;

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
}
