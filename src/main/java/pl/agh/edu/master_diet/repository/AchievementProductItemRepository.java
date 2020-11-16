package pl.agh.edu.master_diet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.agh.edu.master_diet.core.model.database.AchievementProductItem;

import java.util.List;

public interface AchievementProductItemRepository extends JpaRepository<AchievementProductItem, Long> {

    List<AchievementProductItem> findByAchievementIdIn(List<Long> achievementIds);
}
