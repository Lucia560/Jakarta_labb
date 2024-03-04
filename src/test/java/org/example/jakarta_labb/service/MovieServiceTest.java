package org.example.jakarta_labb.service;

import org.example.jakarta_labb.dto.MovieDto;
import org.example.jakarta_labb.entity.Movie;
import org.example.jakarta_labb.exceptionmapper.MovieNotFoundException;
import org.example.jakarta_labb.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Angela Gustafsson, anggus-1
 */
class MovieServiceTest {
    static final UUID MOVIE_ID = UUID.randomUUID();

    MovieService movieService;

    @BeforeEach
    void setUp() {
        movieService = new MovieService(mock(MovieRepository.class));

        Movie movie = new Movie();
        movie.setId(MOVIE_ID);
        movie.setGenre("Adventure");
        movie.setRating(7.7);
        movie.setTitle("Goonies");
        movie.setReleaseYear(1985);

        when(movieService.movieRepository.findAllMovies()).thenReturn(List.of(movie));
        when(movieService.movieRepository.findMovieById(MOVIE_ID)).thenReturn(movie);
    }

    @Test
    void getAllMovies() {
        assertEquals(1, movieService.getAllMovies().movieDtos().size());
    }

    @Test
    void getMovieById() {
        assertNotNull(movieService.getMovieById(MOVIE_ID));
        assertThrows(MovieNotFoundException.class, () -> movieService.getMovieById(UUID.randomUUID()));
    }

    @Test
    void addMovie() {
        when(movieService.movieRepository.saveMovie(any())).then(i -> i.getArgument(0));
        assertNotNull(movieService.addMovie(
            new MovieDto(UUID.randomUUID(), "Die Hard", "Romantic comedy", 1988, 8.2))
        );
        verify(movieService.movieRepository, times(1)).saveMovie(any());
    }

    @Test
    void updateMovie() {
        when(movieService.movieRepository.updateMovie(any())).then(i -> i.getArgument(0));
        MovieDto updatedMovie = movieService.updateMovie(
            MOVIE_ID,
            new MovieDto(UUID.randomUUID(), "Goonies", "Adventure", 1985, 10.0)
        );
        assertEquals(10.0, updatedMovie.rating());
    }

    @Test
    void deleteMovie() {
        movieService.deleteMovie(MOVIE_ID);
        verify(movieService.movieRepository, times(1)).deleteMovie(any());
        assertThrows(MovieNotFoundException.class, () -> movieService.deleteMovie(UUID.randomUUID()));
    }
}
