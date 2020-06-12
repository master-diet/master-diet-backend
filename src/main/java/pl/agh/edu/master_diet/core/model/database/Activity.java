package pl.agh.edu.master_diet.core.model.database;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "activity")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Double burnedCalories;

    private Integer defaultReps;

    private Integer defaultSets;

    private Double defaultTime;
}
