package pl.agh.edu.master_diet.service.converter;

import org.springframework.stereotype.Component;
import pl.agh.edu.master_diet.core.model.database.Product;
import pl.agh.edu.master_diet.core.model.rest.product_browser.BaseProductInfo;

@Component
public final class ProductConverter
        implements GenericConverter<Product, BaseProductInfo> {

    private static final double GRAMS_PER_CALORIES_VALUE = 100.0;

    @Override
    public BaseProductInfo createFrom(final Product dto) {
        return BaseProductInfo.builder()
                .id(dto.getId())
                .name(dto.getName())
                .calories((long) (dto.getCalories() / (GRAMS_PER_CALORIES_VALUE / dto.getDefaultValue())))
                .defaultValue(dto.getDefaultValue().longValue())
                .unit(dto.getUnit())
                .build();
    }
}
