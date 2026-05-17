package lab1.decorators;

import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;
import lab1.dao.IMovieDAO;
import lab1.entities.Movie;

@Decorator
public abstract class MovieDecorator implements IMovieDAO {

    @Inject
    @Delegate
    @Any
    private IMovieDAO delegate;

    @Override
    public Movie update(Movie movie) {
        if (movie.getTitle() != null && !movie.getTitle().startsWith("Decorator: ")) {
            movie.setTitle("Decorator: " + movie.getTitle());
        }

        return delegate.update(movie);
    }
}