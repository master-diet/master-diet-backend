package pl.agh.edu.master_diet.core.model.database;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.codehaus.jackson.annotate.JsonBackReference;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class UserPlan {

    @Id
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
    @JsonBackReference
    private User user;

}
