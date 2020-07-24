package pl.agh.edu.master_diet.core.model.rest;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AchievementsResponse {

    private String name;
    private String description;
    private Integer points;
    private Integer completeCondition;
    private byte[] photo;
    private Integer progress;
    private LocalDateTime completedDate;
}
