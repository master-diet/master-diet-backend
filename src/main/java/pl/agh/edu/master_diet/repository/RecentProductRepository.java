package pl.agh.edu.master_diet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.edu.master_diet.core.model.database.RecentProduct;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecentProductRepository extends JpaRepository<RecentProduct, Long> {

    List<RecentProduct> findByUserId(Long userId);

    @Query("SELECT RP FROM RecentProduct RP " +
            "WHERE RP.user.id = ?1 " +
            "AND DATE(RP.mealTime) = ?2 " +
            "ORDER BY RP.mealTime")
    List<RecentProduct> findByUserAndMealTimeDate(Long userId, LocalDate date);

    @Query("SELECT SUM(RP.product.calories * (RP.portion / 100.0) * RP.amount) FROM RecentProduct RP " +
            "WHERE RP.user.id = ?1 " +
            "AND DATE(RP.mealTime) = ?2 ")
    Optional<Integer> getCaloriesConsumed(Long userId, LocalDate date);

    @Transactional
    Long deleteByUserIdAndId(Long userId, Long id);
}
