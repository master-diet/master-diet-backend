package pl.agh.edu.master_diet.repository;

import lombok.Getter;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class MasterDietPersistence {

    @Getter
    @PersistenceContext
    protected EntityManager entityManager;

    public Session getSession(final EntityManager entityManager) {
        return entityManager.unwrap(Session.class);
    }
}
