package pl.agh.edu.master_diet.core.model.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BmiParameters {

    private Double weight;
    private Integer height;
}
