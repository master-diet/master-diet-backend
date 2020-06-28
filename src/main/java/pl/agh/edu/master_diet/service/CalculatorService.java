package pl.agh.edu.master_diet.service;

import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.rest.calculator.bmi.CalculateBmiResponse;
import pl.agh.edu.master_diet.core.model.shared.BmiParameters;

@Service
public class CalculatorService {

    public CalculateBmiResponse calculateBmi(final BmiParameters bmiParameters) {
        final double bmi = bmiParameters.getWeight() / bmiParameters.getHeight() * bmiParameters.getHeight();

        return CalculateBmiResponse.builder()
                .bmi(Precision.round(bmi, 2))
                .build();
    }
}
