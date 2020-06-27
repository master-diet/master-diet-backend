package pl.agh.edu.master_diet.service.converter;

import org.springframework.stereotype.Component;
import pl.agh.edu.master_diet.core.model.database.Product;
import pl.agh.edu.master_diet.core.model.rest.product_browser.BaseProductInfo;

@Component
public final class ProductConverter
        implements GenericConverter<Product, BaseProductInfo> {

    @Override
    public BaseProductInfo createFrom(final Product dto) {
        return BaseProductInfo.builder()
                .name(dto.getName())
                .calories(dto.getCalories().longValue())
                .defaultValue(dto.getDefaultValue().longValue())
                .build();
    }
}
