package lab1.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lab1.entities.Movie;
import java.util.List;

@ApplicationScoped
public class MovieDAO {
    @PersistenceContext
    private EntityManager em;

    public void persist(Movie movie) {
        this.em.persist(movie);
    }

    public Movie findOne(Long id) {
        return em.find(Movie.class, id);
    }

    public Movie update(Movie movie) {
        return em.merge(movie);
    }

    public List<Movie> loadAll() {
        return em.createQuery("select m from Movie as m", Movie.class).getResultList();
    }
}