package pl.agh.edu.master_diet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.agh.edu.master_diet.core.model.database.UserWeight;

public interface UserWeightRepository extends JpaRepository<UserWeight, Long> {


}
