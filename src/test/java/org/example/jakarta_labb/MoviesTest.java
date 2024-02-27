package org.example.jakarta_labb;

import static org.junit.jupiter.api.Assertions.*;

import org.example.jakarta_labb.dto.MovieDto;
import org.example.jakarta_labb.dto.Movies;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

class MoviesTest {

    @Test
    @DisplayName("Get the correct size of the movie list")
    void getCorrectSizeOfMovieList() {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();

        MovieDto movieDto1 = new MovieDto(uuid1, "Movie1", "Action", 2005, 8.5);
        MovieDto movieDto2 = new MovieDto(uuid2, "Movie2", "Comedy", 2024, 3.5);

        LocalDateTime updated = LocalDateTime.of(2024, 2, 27, 17, 45);

        Movies movies = new Movies(Arrays.asList(movieDto1, movieDto2), updated);

        assertEquals(2, movies.movieDtos().size());
    }
}
