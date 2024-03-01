package org.example.jakarta_labb.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.jakarta_labb.dto.MovieDto;
import org.example.jakarta_labb.dto.Movies;
import org.example.jakarta_labb.entity.Movie;
import org.example.jakarta_labb.repository.MovieRepository;

import java.util.UUID;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {

    @Inject
    private MovieRepository movieRepository;

    @GET
    public Movies getAllMovies() {
        return Movies.fromMovies(movieRepository.findAllMovies());
    }

    @GET
    @Path("/{id}")
    public MovieDto getMovieById(@PathParam("id") UUID id) {
        Movie movie = movieRepository.findMovieById(id);
        MovieDto movieDto = MovieDto.fromEntity(movie);
        return movieDto;
    }

    @POST
    public MovieDto addMovie(MovieDto movieDto) {
        Movie movie = MovieDto.map(movieDto);
        Movie savedMovie = movieRepository.saveMovie(movie);
        MovieDto savedMovieDto = MovieDto.fromEntity(savedMovie);
        return savedMovieDto;
    }

    @PUT
    @Path("/{id}")
    public MovieDto updateMovie(@PathParam("id") UUID id, MovieDto movieDto) {
        Movie movie = movieRepository.findMovieById(id);

        movie.setTitle(movie.getTitle());
        movie.setGenre(movieDto.genre());
        movie.setReleaseYear(movieDto.releaseYear());
        movie.setRating(movieDto.rating());

        Movie updateMovie = movieRepository.updateMovie(movie);
        MovieDto updatedMovieDto = MovieDto.fromEntity(updateMovie);
        return updatedMovieDto;
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
