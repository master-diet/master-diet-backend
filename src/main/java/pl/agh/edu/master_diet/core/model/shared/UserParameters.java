package pl.agh.edu.master_diet.core.model.shared;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserParameters {

    private int userId;
    private Integer age;
    private Gender gender;
    private Integer height;
    private Integer weight;
    private ActivityLevel activityLevel;
}
