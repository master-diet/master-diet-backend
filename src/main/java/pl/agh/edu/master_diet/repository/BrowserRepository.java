package pl.agh.edu.master_diet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BrowserRepository<T> extends JpaRepository<T, Long> {

    @Query("SELECT T FROM #{#entityName} T " +
            "WHERE LOWER(T.name) LIKE %?1% " +
            "ORDER BY T.approvals DESC")
    List<T> findBySearchTerm(String searchTerm);
}
