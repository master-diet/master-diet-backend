package pl.agh.edu.master_diet.core.model.database;

import lombok.*;
import pl.agh.edu.master_diet.core.model.rest.diary.SingleUserActivityInfo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_activity")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer burnedCalories;

    @Column(nullable = false, updatable = false)
    private LocalDateTime auditDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

    public SingleUserActivityInfo createSingleResponseForActivity() {

        return SingleUserActivityInfo.builder()
                .activityName(activity.getName())
                .activityTime(auditDate)
                .userActivityId(id)
                .caloriesBurned(burnedCalories)
                .build();
    }
}
