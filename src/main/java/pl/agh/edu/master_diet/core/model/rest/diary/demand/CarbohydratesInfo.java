package pl.agh.edu.master_diet.core.model.rest.diary.demand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CarbohydratesInfo extends AbstractNutrientInfo {

    private float carbohydrates;
}
