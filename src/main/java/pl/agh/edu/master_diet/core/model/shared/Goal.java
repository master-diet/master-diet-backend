package pl.agh.edu.master_diet.core.model.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Goal {
    LOSE(-0.2),
    STAY(0),
    GAIN(0.2);

    private final double value;
}
