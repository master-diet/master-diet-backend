package pl.agh.edu.master_diet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.edu.master_diet.core.model.database.UserActivity;

import java.time.LocalDate;
import java.util.List;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
    List<UserActivity> findByUserId(Long userId);

    List<UserActivity> findByUserIdAndAuditDate(Long userId, LocalDate date);

    @Transactional
    Long deleteByUserIdAndId(Long userId, Long id);
}
