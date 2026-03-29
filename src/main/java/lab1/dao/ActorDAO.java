package lab1.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lab1.entities.Actor;
import java.util.List;

@ApplicationScoped
public class ActorDAO {
    @PersistenceContext
    private EntityManager em;

    public void persist(Actor actor) {
        this.em.persist(actor);
    }

    public Actor findOne(Long id) {
        return em.find(Actor.class, id);
    }

    public Actor update(Actor actor) {
        return em.merge(actor);
    }

    public List<Actor> loadAll() {
        return em.createQuery("select a from Actor as a", Actor.class).getResultList();
    }
}