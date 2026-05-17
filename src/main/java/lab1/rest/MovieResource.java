package lab1.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lab1.dao.IMovieDAO;
import lab1.entities.Movie;
import java.util.List;

@Path("/movies")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {

    @Inject
    private IMovieDAO movieDAO;


    @GET
    public Response getAllMovies() {
        List<Movie> movies = movieDAO.loadAll();
        return Response.ok(movies).build();
    }


    @GET
    @Path("/{id}")
    public Response getMovieById(@PathParam("id") Long id) {
        Movie movie = movieDAO.findOne(id);
        if (movie == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(movie).build();
    }


    @POST
    @Transactional
    public Response createMovie(Movie movie) {
        movieDAO.persist(movie);
        return Response.status(Response.Status.CREATED).entity(movie).build();
    }


    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateMovie(@PathParam("id") Long id, Movie updatedMovie) {
        Movie existingMovie = movieDAO.findOne(id);
        if (existingMovie == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }


        existingMovie.setTitle(updatedMovie.getTitle());

        movieDAO.update(existingMovie);
        return Response.ok(existingMovie).build();
    }
}