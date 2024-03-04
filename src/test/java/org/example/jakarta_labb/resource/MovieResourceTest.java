package org.example.jakarta_labb.resource;

import org.example.jakarta_labb.dto.Movies;
import org.example.jakarta_labb.entity.Movie;
import org.example.jakarta_labb.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovieResourceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieResource movieResource;

    @Test
    void testGetAllMovies() {
        // Prepare a list of Movie entities
        List<Movie> mockMovies = IntStream.range(0, 2)
            .mapToObj(i -> {
                Movie movie = mock(Movie.class);
                // Configure your mock Movie as needed
                return movie;
            })
            .collect(Collectors.toList());

        // Mock the MovieRepository response
        when(movieRepository.findAllMovies()).thenReturn(mockMovies);

        // Call the method under test
        Movies response = movieResource.getAllMovies();

        // Validate the response
        assertNotNull(response);
        assertEquals(2, response.movieDtos().size());
        assertNotNull(response.updated());
        assertTrue(response.updated().isBefore(LocalDateTime.now().plusSeconds(1))); // Ensure 'updated' is recent
    }



    @Test
    void getMovieById() {
    }

    @Test
    void addMovie() {
    }

    @Test
    void updateMovie() {
    }

    @Test
    void deleteMovie() {
    }
}
