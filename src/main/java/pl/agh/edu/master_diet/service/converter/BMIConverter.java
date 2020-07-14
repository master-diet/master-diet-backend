package pl.agh.edu.master_diet.service.converter;

import org.springframework.stereotype.Component;
import pl.agh.edu.master_diet.core.model.rest.calculator.bmi.CalculateBMIRequest;
import pl.agh.edu.master_diet.core.model.shared.BMIParameters;

@Component
public class BMIConverter
        implements GenericConverter<CalculateBMIRequest, BMIParameters> {

    @Override
    public BMIParameters createFrom(CalculateBMIRequest dto) {
        return BMIParameters.builder()
                .height(dto.getHeight())
                .weight(dto.getWeight())
                .build();
    }
}
