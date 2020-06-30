package pl.agh.edu.master_diet.service;

import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.rest.calculator.bmi.CalculateBmiResponse;
import pl.agh.edu.master_diet.core.model.shared.BmiParameters;
import pl.agh.edu.master_diet.core.model.shared.WeightRange;

@Service
public class CalculatorService {

    public CalculateBmiResponse calculateBmi(final BmiParameters bmiParameters) {
        final Double weight = bmiParameters.getWeight();
        final Double height = bmiParameters.getHeight() / 100.0d;
        final double bmi = weight / (height * height);

        return CalculateBmiResponse.builder()
                .bmi(Precision.round(bmi, 2))
                .weightRange(WeightRange.getWeightRangeFromBmi(bmi))
                .build();
    }
}
