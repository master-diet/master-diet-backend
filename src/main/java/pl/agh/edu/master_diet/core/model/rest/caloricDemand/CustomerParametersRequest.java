package pl.agh.edu.master_diet.core.model.rest.caloricDemand;

import lombok.NonNull;
import pl.agh.edu.master_diet.core.model.shared.ActivityLevel;
import pl.agh.edu.master_diet.core.model.shared.Gender;


public class CustomerParametersRequest {

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
