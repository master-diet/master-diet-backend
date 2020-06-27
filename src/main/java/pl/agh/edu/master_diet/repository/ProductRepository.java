package pl.agh.edu.master_diet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.agh.edu.master_diet.core.model.database.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT P FROM Product P " +
            "WHERE LOWER(P.name) LIKE LOWER(concat('%', concat(?1, '%')))" +
            "ORDER BY P.approvals DESC")
    List<Product> findBySearchTerm(String searchTerm);
}
