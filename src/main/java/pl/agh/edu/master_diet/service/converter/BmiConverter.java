package pl.agh.edu.master_diet.service.converter;

import org.springframework.stereotype.Component;
import pl.agh.edu.master_diet.core.model.rest.calculator.bmi.CalculateBmiRequest;
import pl.agh.edu.master_diet.core.model.shared.BmiParameters;

@Component
public class BmiConverter
        implements GenericConverter<CalculateBmiRequest, BmiParameters> {

    @Override
    public BmiParameters createFrom(CalculateBmiRequest dto) {
        return BmiParameters.builder()
                .height(dto.getHeight())
                .weight(dto.getWeight())
                .build();
    }
}
