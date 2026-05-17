package lab1.dao;

import lab1.entities.Movie;
import java.util.List;

public interface IMovieDAO {
    void persist(Movie movie);
    Movie findOne(Long id);
    Movie update(Movie movie);
    List<Movie> loadAll();
}