package pl.agh.edu.master_diet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.agh.edu.master_diet.core.model.database.Achievement;
import pl.agh.edu.master_diet.core.model.shared.AchievementType;

import java.util.List;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {

    @Query("SELECT A FROM Achievement A " +
            "WHERE A.achievementType = ?1 " +
            "AND (SELECT COUNT(UA) FROM UserAchievement UA " +
            "   WHERE UA.achievement.id = A.id " +
            "   AND UA.completedDate IS NOT NULL" +
            "   AND UA.user.id = ?2) = 0")
    List<Achievement> findUncompletedUserAchievements(AchievementType achievementType, Long userId);
}
