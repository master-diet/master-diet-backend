package pl.agh.edu.master_diet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.agh.edu.master_diet.core.model.database.User;
import pl.agh.edu.master_diet.core.model.database.UserWeight;

import java.util.Optional;

@Repository
public interface UserWeightRepository extends JpaRepository<UserWeight, Long> {

    Optional<UserWeight> findFirstByUserOrderByCreationDateDesc(User user);
}
