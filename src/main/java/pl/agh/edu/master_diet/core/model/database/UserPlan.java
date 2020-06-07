package pl.agh.edu.master_diet.core.model.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.agh.edu.master_diet.core.model.common.AuthProvider;
import pl.agh.edu.master_diet.core.model.rest.userPlan.UserPlanResponse;
import pl.agh.edu.master_diet.core.model.shared.ActivityLevel;
import pl.agh.edu.master_diet.core.model.shared.Goal;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_plan")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer calories;

    @Column(nullable = false)
    private Float proteins;

    @Column(nullable = false)
    private Float fat;

    @Column(nullable = false)
    private Float carbohydrates;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Goal goal;

    @OneToOne
    @MapsId
    private User user;

    public UserPlanResponse map2UserPlanResponse(){
        return UserPlanResponse.builder()
                .userId(getId())
                .calories(getCalories())
                .carbohydrates(getCarbohydrates())
                .fat(getFat())
                .proteins(getProteins())
                .build();
    }

}
