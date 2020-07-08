package pl.agh.edu.master_diet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.agh.edu.master_diet.core.model.database.RecentProduct;

import java.util.List;

@Repository
public interface RecentProductsRepository extends JpaRepository<RecentProduct, Long> {

    List<RecentProduct> findByUserId(Long userId);
}
