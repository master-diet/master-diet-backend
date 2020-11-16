package pl.agh.edu.master_diet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.agh.edu.master_diet.core.model.database.AchievementActivityItem;

import java.util.List;

public interface AchievementActivityItemRepository extends JpaRepository<AchievementActivityItem, Long> {

    List<AchievementActivityItem> findByAchievementIdIn(List<Long> achievementIds);
}
