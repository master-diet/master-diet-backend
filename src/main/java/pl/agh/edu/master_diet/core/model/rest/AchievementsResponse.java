package pl.agh.edu.master_diet.core.model.rest;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class AchievementsResponse {

    @EqualsAndHashCode.Include()
    private String name;

    @EqualsAndHashCode.Include()
    private String description;

    @EqualsAndHashCode.Include()
    private Integer points;

    @EqualsAndHashCode.Include()
    private Integer completeCondition;
    private byte[] photo;
    private Integer progress;
    private LocalDateTime completedDate;
}
