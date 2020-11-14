package pl.agh.edu.master_diet.core.model.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AchievementType {
    PRODUCTS,
    CALORIES,
    CARBOHYDRATES,
    FAT,
    PROTEINS,
    ACTIVITIES,
    LOGGING_IN
}
