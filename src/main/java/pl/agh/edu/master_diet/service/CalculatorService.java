package pl.agh.edu.master_diet.service;

import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.rest.calculator.bmi.CalculateBMIResponse;
import pl.agh.edu.master_diet.core.model.shared.BMIParameters;
import pl.agh.edu.master_diet.core.model.shared.WeightRange;

@Service
public class CalculatorService {

    private static final double HEIGHT_DIVISOR = 100.d;

    public CalculateBMIResponse calculateBMI(final BMIParameters bmiParameters) {
        final Double weight = bmiParameters.getWeight();
        final Double heightInMetres = bmiParameters.getHeight() / HEIGHT_DIVISOR;
        final double userBMI = weight / (heightInMetres * heightInMetres);

        return CalculateBMIResponse.builder()
                .userBMI(Precision.round(userBMI, 2))
                .weightRange(WeightRange.getWeightRangeFromBmi(userBMI))
                .build();
    }
}
