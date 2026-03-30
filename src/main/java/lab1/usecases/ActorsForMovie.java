package lab1.usecases;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lab1.dao.ActorDAO;
import lab1.dao.MovieDAO;
import lab1.entities.Actor;
import lab1.entities.Movie;
import java.io.Serializable;

@Named
@RequestScoped
public class ActorsForMovie implements Serializable {

    @Inject private MovieDAO movieDAO;
    @Inject private ActorDAO actorDAO;

    private Movie movie;
    private Actor actorToCreate = new Actor();
    private Long movieId;
    private Long actorIdToAssign;


    public void init() {
        if (this.movieId != null) {
            this.movie = movieDAO.findOne(this.movieId);
        }
    }

    @Transactional
    public String addActor() {
        if (this.movie == null) {
            init();
        }
        actorToCreate.getMovies().add(this.movie);
        this.movie.getActors().add(actorToCreate);

        actorDAO.persist(actorToCreate);
        movieDAO.update(this.movie);

        return "actors?faces-redirect=true&movieId=" + this.movieId;
    }

    @Transactional
    public String assignActor() {
        if (this.movie == null) {
            init();
        }
        if (this.actorIdToAssign != null) {
            Actor existingActor = actorDAO.findOne(this.actorIdToAssign);
            if (!this.movie.getActors().contains(existingActor)) {
                this.movie.getActors().add(existingActor);
                existingActor.getMovies().add(this.movie);

                actorDAO.update(existingActor);
                movieDAO.update(this.movie);
            }
        }
        return "actors?faces-redirect=true&movieId=" + this.movieId;
    }

    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }
    public Actor getActorToCreate() { return actorToCreate; }
    public void setActorToCreate(Actor actorToCreate) { this.actorToCreate = actorToCreate; }
    public Long getMovieId() { return movieId; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }
    public Long getActorIdToAssign() { return actorIdToAssign; }
    public void setActorIdToAssign(Long actorIdToAssign) { this.actorIdToAssign = actorIdToAssign; }
}