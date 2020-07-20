package pl.agh.edu.master_diet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.agh.edu.master_diet.core.model.database.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
