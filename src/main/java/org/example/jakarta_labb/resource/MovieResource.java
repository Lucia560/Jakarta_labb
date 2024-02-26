package org.example.jakarta_labb.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.jakarta_labb.entity.Movie;
import org.example.jakarta_labb.repository.MovieRepository;

import java.util.List;
import java.util.UUID;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {

    private final MovieRepository movieRepository;

    @Inject
    public MovieResource(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GET
    public List<Movie> getAllMovies() {
        return movieRepository.findAllMovies();
    }

    @GET
    @Path("/{id}")
    public Movie getMovieById(@PathParam("id") UUID id) {
        return movieRepository.findMovieById(id);
    }

    @POST
    public Movie addMovie(Movie movie) {
        return movieRepository.saveMovie(movie);
    }

    @PUT
    @Path("/{id}")
    public Movie updateMovie(@PathParam("id") UUID id, Movie movie) {
        movie.setId(id);
        return movieRepository.saveMovie(movie);
    }

    @DELETE
    @Path("/{id}")
    public void deleteMovie(@PathParam("id") UUID id) {
        Movie movie = movieRepository.findMovieById(id);
        if (movie != null) {
            movieRepository.deleteMovie(movie);
        } else {
            throw new NotFoundException("Movie not found with id: " + id);
        }
    }
}
