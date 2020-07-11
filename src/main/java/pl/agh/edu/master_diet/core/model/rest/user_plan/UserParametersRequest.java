package pl.agh.edu.master_diet.core.model.rest.user_plan;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
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