package lab1.usecases;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lab1.dao.ActorDAO;
import lab1.entities.Actor;
import java.io.Serializable;
import java.util.Map;

@Named
@RequestScoped
public class ActorDetails implements Serializable {
    @Inject
    private ActorDAO actorDAO;

    private Actor actor;
    private Long actorId;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String idParam = params.get("actorId");
        if (idParam != null) {
            this.actorId = Long.parseLong(idParam);
            this.actor = actorDAO.findOne(this.actorId);
        }
    }

    public Actor getActor() { return actor; }
    public void setActor(Actor actor) { this.actor = actor; }
    public Long getActorId() { return actorId; }
    public void setActorId(Long actorId) { this.actorId = actorId; }
}