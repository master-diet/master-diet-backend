package pl.agh.edu.master_diet.core.model.rest.userPlan;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import pl.agh.edu.master_diet.core.model.shared.ActivityLevel;
import pl.agh.edu.master_diet.core.model.shared.Gender;
import pl.agh.edu.master_diet.core.model.shared.Goal;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserParametersRequest {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    @NonNull
    private Long userId;

    @NonNull
    @JsonFormat(pattern = DATE_FORMAT)
    private LocalDate birthDate;

    @NonNull
    private Gender gender;

    @NonNull
    private Integer height;

    @NonNull
    private Double weight;

    @NonNull
    private ActivityLevel activityLevel;

    @NonNull
    private Goal goal;

    @NonNull
    private Double fatPreferencesPercentage;
}
