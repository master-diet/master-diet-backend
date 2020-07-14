package pl.agh.edu.master_diet.core.model.rest.gamification;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class PointsRequest {

    @NonNull
    private Long userId;

    @NonNull
    private Integer points;

}
