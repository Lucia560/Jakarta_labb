package org.example.jakarta_labb.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.example.jakarta_labb.dto.MovieDto;
import org.example.jakarta_labb.dto.Movies;
import org.example.jakarta_labb.entity.Movie;
import org.example.jakarta_labb.exceptionmapper.MovieNotFoundException;
import org.example.jakarta_labb.repository.MovieRepository;

import java.util.UUID;

/**
 * @author Angela Gustafsson, anggus-1
 */
@ApplicationScoped
@Transactional
public class MovieService {


    final MovieRepository movieRepository;

    @Inject
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Dummy constructor for CDI
    public MovieService() {
        this(null);
    }

    public Movies getAllMovies() {
        return Movies.fromMovies(movieRepository.findAllMovies());
    }


    public MovieDto getMovieById(UUID id) {
        Movie movie = findByIdOrThrow(id);
        MovieDto movieDto = MovieDto.fromEntity(movie);
        return movieDto;
    }


    public MovieDto addMovie(MovieDto movieDto) {
        Movie movie = MovieDto.map(movieDto);
        Movie savedMovie = movieRepository.saveMovie(movie);
        MovieDto savedMovieDto = MovieDto.fromEntity(savedMovie);
        return savedMovieDto;
    }


    public MovieDto updateMovie(UUID id, MovieDto movieDto) {
        Movie movie = findByIdOrThrow(id);

        movie.setTitle(movie.getTitle());
        movie.setGenre(movieDto.genre());
        movie.setReleaseYear(movieDto.releaseYear());
        movie.setRating(movieDto.rating());

        Movie updateMovie = movieRepository.updateMovie(movie);
        MovieDto updatedMovieDto = MovieDto.fromEntity(updateMovie);
        return updatedMovieDto;
    }

    public void deleteMovie(UUID id) {
        Movie movie = findByIdOrThrow(id);
        movieRepository.deleteMovie(movie);
    }

    private Movie findByIdOrThrow(UUID id){
        Movie movie = movieRepository.findMovieById(id);
        if (movie == null) {
            throw new MovieNotFoundException("Movie not found with id: " + id);
        }
        return movie;
    }
}
