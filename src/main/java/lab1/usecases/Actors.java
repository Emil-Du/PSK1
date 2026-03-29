package lab1.usecases;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lab1.dao.ActorDAO;
import lab1.entities.Actor;
import java.util.List;

@Named
@RequestScoped
public class Actors {
    @Inject
    private ActorDAO actorDAO;

    private List<Actor> allActors;

    @PostConstruct
    public void init() {
        this.allActors = actorDAO.loadAll();
    }

    public List<Actor> getAllActors() {
        return allActors;
    }
}