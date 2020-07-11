package pl.agh.edu.master_diet.core.model.shared;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.agh.edu.master_diet.exception.UnknownWeightRangeException;

@Getter
@RequiredArgsConstructor
public enum WeightRange {

    UNDERWEIGHT(18.49d),
    NORMAL(24.9d),
    OVERWEIGHT(29.9d),
    OBESE(Float.MAX_VALUE);

    private final double upperLimit;

    public static WeightRange getWeightRangeFromBmi(final double bmi) {
        for (WeightRange range : WeightRange.values())
            if (Double.compare(bmi, range.getUpperLimit()) < 0) return range;

        throw new UnknownWeightRangeException("Unknown weight range for bmi: " + bmi);
    }
}
