package pl.agh.edu.master_diet.core.model.database;

import lombok.*;
import org.codehaus.jackson.annotate.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "user_gamification_detail")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGamificationDetail {

    @Id
    private Long id;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer points;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer level;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer cheatTokens;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer completeCondition;

    @OneToOne
    @MapsId
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_challenge_id")
    private DailyChallenge dailyChallenge;
}
