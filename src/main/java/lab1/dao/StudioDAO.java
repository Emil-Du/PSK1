package lab1.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lab1.entities.Studio;
import java.util.List;

@ApplicationScoped
public class StudioDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Studio> loadAll() {
        return em.createQuery("select s from Studio as s", Studio.class).getResultList();
    }

    public void persist(Studio studio) {
        this.em.persist(studio);
    }

    // Būtinas navigacijai: randa studiją pagal ID iš URL parametro
    public Studio findOne(Long id) {
        return em.find(Studio.class, id);
    }

    // Būtinas pridedant filmus: atnaujina studijos objektą su nauju filmu
    public void update(Studio studio) {
        this.em.merge(studio);
    }
}