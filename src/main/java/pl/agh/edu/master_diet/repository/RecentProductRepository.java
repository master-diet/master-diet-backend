package pl.agh.edu.master_diet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.agh.edu.master_diet.core.model.database.RecentProduct;

public interface RecentProductRepository extends JpaRepository<RecentProduct, Long> {
}
