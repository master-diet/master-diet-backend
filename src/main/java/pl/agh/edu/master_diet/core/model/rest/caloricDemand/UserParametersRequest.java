package pl.agh.edu.master_diet.core.model.rest.caloricDemand;

import lombok.*;
import pl.agh.edu.master_diet.core.model.shared.ActivityLevel;
import pl.agh.edu.master_diet.core.model.shared.Gender;

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
}
