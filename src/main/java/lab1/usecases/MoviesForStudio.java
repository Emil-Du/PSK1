package lab1.usecases;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import lab1.dao.IMovieDAO;
import lab1.dao.MovieDAO;
import lab1.dao.StudioDAO;
import lab1.entities.Movie;
import lab1.entities.Studio;
import lab1.interceptors.LoggedInvocation;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Named
@ViewScoped
public class MoviesForStudio implements Serializable {
    @Inject
    private StudioDAO studioDAO;

    @Inject
    private IMovieDAO movieDAO;

    @Inject
    private GenerateMovieReport reportGenerator;

    private Studio studio;
    private Movie movieToCreate = new Movie();
    private Long studioId;
    private Future<String> reportStatus = null;
    private boolean optimisticLockError = false;

    public void init() {
        if (this.studioId != null) {
            this.studio = studioDAO.findOne(this.studioId);
        }
    }

    public void startReportGeneration() {
        this.reportStatus = reportGenerator.generateReport();
    }

    public String getReportStatusMessage() throws ExecutionException, InterruptedException {
        if (reportStatus == null) {
            return "Ataskaita dar nepradėta.";
        } else if (reportStatus.isDone()) {
            return reportStatus.get();
        } else {
            return "Skaičiuojama...";
        }
    }

    @Transactional
    public String createMovie() {
        if (this.studio == null) {
            init();
        }

        movieToCreate.setStudio(this.studio);
        movieDAO.persist(movieToCreate);
        return "movies?faces-redirect=true&studioId=" + this.studioId;
    }



    @Transactional
    public void updateMovieTitle(Movie movie) {
        try {
            optimisticLockError = false;
            movieDAO.update(movie);
        } catch (OptimisticLockException e) {
            optimisticLockError = true;
        }
    }


    public boolean isOptimisticLockError() {
        return optimisticLockError;
    }

    public Studio getStudio() { return studio; }
    public void setStudio(Studio studio) { this.studio = studio; }
    public Movie getMovieToCreate() { return movieToCreate; }
    public void setMovieToCreate(Movie movieToCreate) { this.movieToCreate = movieToCreate; }
    public Long getStudioId() { return studioId; }
    public void setStudioId(Long studioId) { this.studioId = studioId; }
}