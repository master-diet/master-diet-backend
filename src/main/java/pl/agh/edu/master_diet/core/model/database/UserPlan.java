package pl.agh.edu.master_diet.core.model.database;

import lombok.*;
import pl.agh.edu.master_diet.core.model.rest.userPlan.UserPlanResponse;
import pl.agh.edu.master_diet.core.model.shared.ActivityLevel;
import pl.agh.edu.master_diet.core.model.shared.Goal;

import javax.persistence.*;

@Entity
@Table(name = "user_plan")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer calories;

    @Column(nullable = false)
    private Integer proteins;

    @Column(nullable = false)
    private Integer fat;

    @Column(nullable = false)
    private Integer carbohydrates;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Goal goal;

    @Column(nullable = false)
    private Double currentWeight;

    @OneToOne
    @MapsId
    private User user;

    public UserPlanResponse map2UserPlanResponse() {
        return UserPlanResponse.builder()
                .userId(getId())
                .calories(getCalories())
                .carbohydrates(getCarbohydrates())
                .fat(getFat())
                .proteins(getProteins())
                .build();
    }

}
