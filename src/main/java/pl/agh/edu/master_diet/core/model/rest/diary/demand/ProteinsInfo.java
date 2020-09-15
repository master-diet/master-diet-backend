package pl.agh.edu.master_diet.core.model.rest.diary.demand;

import lombok.Getter;

@Getter
public class ProteinsInfo extends AbstractNutrientInfo {

    @Override
    public String getDescription() {
        return "Proteins";
    }
}
