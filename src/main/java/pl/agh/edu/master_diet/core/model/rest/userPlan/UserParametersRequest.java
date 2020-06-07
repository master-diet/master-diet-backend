package pl.agh.edu.master_diet.core.model.rest.userPlan;

import lombok.*;
import pl.agh.edu.master_diet.core.model.shared.ActivityLevel;
import pl.agh.edu.master_diet.core.model.shared.Gender;
import pl.agh.edu.master_diet.core.model.shared.Goal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserParametersRequest {

    @NonNull
    private int userId;
    @NonNull
    private Integer age;
    @NonNull
    private Gender gender;
    @NonNull
    private Integer height;
    @NonNull
    private Integer weight;
    @NonNull
    private ActivityLevel activityLevel;
    @NonNull
    private Goal goal;
}
