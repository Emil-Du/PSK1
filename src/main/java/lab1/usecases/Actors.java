package lab1.usecases;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import lab1.dao.ActorDAO;
import lab1.entities.Actor;
import java.util.List;

@Model
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