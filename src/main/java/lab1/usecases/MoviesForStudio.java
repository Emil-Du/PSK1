package lab1.usecases;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lab1.dao.MovieDAO;
import lab1.dao.StudioDAO;
import lab1.entities.Movie;
import lab1.entities.Studio;
import java.io.Serializable;
import java.util.Map;

@Named
@RequestScoped
public class MoviesForStudio implements Serializable {
    @Inject
    private StudioDAO studioDAO;

    @Inject
    private MovieDAO movieDAO;

    private Studio studio;
    private Movie movieToCreate = new Movie();
    private Long studioId;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String sId = params.get("studioId");
        if (sId != null) {
            this.studioId = Long.parseLong(sId);
            this.studio = studioDAO.findOne(this.studioId);
        }
    }

    @Transactional
    public String createMovie() {
        if (this.studio == null && this.studioId != null) {
            this.studio = studioDAO.findOne(this.studioId);
        }

        movieToCreate.setStudio(this.studio);
        movieDAO.persist(movieToCreate);
        return "movies?faces-redirect=true&studioId=" + this.studioId;
    }

    public Studio getStudio() { return studio; }
    public void setStudio(Studio studio) { this.studio = studio; }
    public Movie getMovieToCreate() { return movieToCreate; }
    public void setMovieToCreate(Movie movieToCreate) { this.movieToCreate = movieToCreate; }
    public Long getStudioId() { return studioId; }
    public void setStudioId(Long studioId) { this.studioId = studioId; }
}