package pl.agh.edu.master_diet.core.model.rest.calculator.bmi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculateBmiRequest {

    private Double weight;
    private Integer height;
}