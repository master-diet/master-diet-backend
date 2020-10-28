package pl.agh.edu.master_diet.core.model.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityParameters {

    private LocalDateTime activityTime;
    private Float amount;
    private Float time;
    private Unit timeUnit;
    private Long userId;
    private Long activityId;

}
