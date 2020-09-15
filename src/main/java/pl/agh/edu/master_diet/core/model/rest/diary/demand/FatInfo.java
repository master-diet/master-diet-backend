package pl.agh.edu.master_diet.core.model.rest.diary.demand;

import lombok.Getter;

@Getter
public class FatInfo extends AbstractNutrientInfo {

    @Override
    public String getDescription() {
        return "Fat";
    }
}
