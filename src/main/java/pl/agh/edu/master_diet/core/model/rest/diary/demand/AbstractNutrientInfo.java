package pl.agh.edu.master_diet.core.model.rest.diary.demand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractNutrientInfo {

    protected float sum;

    public void updateSum(final float sum) {
        this.sum += sum;
    }

    public abstract String getDescription();

    public abstract String getIdentifier();
}
