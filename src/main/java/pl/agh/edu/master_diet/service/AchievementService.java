package pl.agh.edu.master_diet.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.Achievement;
import pl.agh.edu.master_diet.core.model.database.UserAchievement;
import pl.agh.edu.master_diet.core.model.rest.AchievementsResponse;
import pl.agh.edu.master_diet.repository.AchievementRepository;
import pl.agh.edu.master_diet.repository.UserAchievementRepository;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AchievementService {

    private final UserAchievementRepository userAchievementRepository;
    private final ConversionService conversionService;
    private final AchievementRepository achievementRepository;

    public Set<AchievementsResponse> getUserAchievements(Long userId) {
        List<UserAchievement> userAchievementList = userAchievementRepository.findByUserId(userId);
        List<Achievement> achievementList = achievementRepository.findAll();
        Set<AchievementsResponse> userAchievementResponses = userAchievementList.stream()
                .map(conversionService::convert)
                .collect(Collectors.toSet());

        Set<AchievementsResponse> responsesSet = achievementList.stream()
                .map(conversionService::convert)
                .collect(Collectors.toSet());

        userAchievementResponses.addAll(responsesSet);
        return userAchievementResponses;
    }
}
