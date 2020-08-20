package pl.agh.edu.master_diet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.agh.edu.master_diet.core.model.database.UserWeight;

import java.util.Optional;

public interface UserWeightRepository extends JpaRepository<UserWeight, Long> {

    Optional<UserWeight> findByUserIdOrderByCreationDateDesc(Long userId);
}
