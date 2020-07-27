package pl.agh.edu.master_diet.service.converter;

import org.springframework.stereotype.Component;
import pl.agh.edu.master_diet.core.model.database.Achievement;
import pl.agh.edu.master_diet.core.model.database.UserAchievement;
import pl.agh.edu.master_diet.core.model.rest.AchievementsResponse;

@Component
public class UserAchievementConverter implements GenericConverter<UserAchievement, AchievementsResponse> {

    @Override
    public AchievementsResponse createFrom(UserAchievement dto) {
        Achievement achievement = dto.getAchievement();
        return AchievementsResponse.builder()
                .name(achievement.getName())
                .description(achievement.getDescription())
                .completeCondition(achievement.getCompleteCondition())
                .photo(achievement.getPhoto())
                .points(achievement.getPoints())
                .progress(dto.getProgress())
                .completedDate(dto.getCompletedDate())
                .build();
    }
}
