package pl.agh.edu.master_diet.core.model.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserParameters {

    private Long userId;

    private LocalDate birthDate;

    private Gender gender;

    private Integer height;

    private Double weight;

    private ActivityLevel activityLevel;

    private Goal goal;

    private Double fatPreferencesPercentage;
}
