package pl.agh.edu.master_diet.core.model.rest;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AchievementSetResponse {

    private Set<AchievementsResponse> achievementsResponseList;
}
