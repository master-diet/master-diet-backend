package pl.agh.edu.master_diet.service.converter;

import org.springframework.stereotype.Component;
import pl.agh.edu.master_diet.core.model.rest.diary.AddRecentProductRequest;
import pl.agh.edu.master_diet.core.model.shared.RecentProductParameters;

@Component
public class RecentProductConverter
        implements GenericConverter<AddRecentProductRequest, RecentProductParameters> {

    @Override
    public RecentProductParameters createFrom(AddRecentProductRequest dto) {
        return RecentProductParameters.builder()
                .amount(dto.getAmount())
                .mealType(dto.getMealType())
                .portion(dto.getPortion())
                .productId(dto.getProductId())
                .userId(dto.getUserId())
                .build();
    }
}
