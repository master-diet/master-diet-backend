package pl.agh.edu.master_diet.service.converter;

import org.springframework.stereotype.Component;
import pl.agh.edu.master_diet.core.model.database.Achievement;
import pl.agh.edu.master_diet.core.model.rest.AchievementsResponse;

@Component
public class AchievementConverter
        implements GenericConverter<Achievement, AchievementsResponse> {

    private final static Float NO_PROGRESS = 0.0f;

    @Override
    public AchievementsResponse createFrom(Achievement dto) {
        return AchievementsResponse.builder()
                .progress(NO_PROGRESS)
                .points(dto.getPoints())
                .imageUrl(dto.getImageUrl())
                .completeCondition(dto.getCompleteCondition())
                .description(dto.getDescription())
                .name(dto.getName())
                .build();
    }
}
