package pl.agh.edu.master_diet.core.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Float completeCondition;
    private String imageUrl;
    private Float progress;
    private LocalDateTime completedDate;
}
