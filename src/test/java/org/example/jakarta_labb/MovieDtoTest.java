package org.example.jakarta_labb;

import static org.junit.jupiter.api.Assertions.*;

import org.example.jakarta_labb.dto.MovieDto;
import org.example.jakarta_labb.entity.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class MovieDtoTest {

    @Test
    @DisplayName("Builder correctly constructs MovieDto")
    void builderCorrectlyConstructsMovieDto() {
        UUID uuid = UUID.randomUUID();
        String name = "Test Movie";
        String genre = "Action";
        int releaseYear = 2015;
        double rating = 4;

        MovieDto movieDto = MovieDto.builder()
            .uuid(uuid)
            .name(name)
            .genre(genre)
            .releaseYear(releaseYear)
            .rating(rating)
            .build();

        assertEquals(uuid, movieDto.uuid());
        assertEquals(name, movieDto.name());
        assertEquals(genre, movieDto.genre());
        assertEquals(releaseYear, movieDto.releaseYear());
        assertEquals(rating, movieDto.rating());
    }

    @Test
    @DisplayName("mapToMovie correctly maps MovieDto to Movie")
    void mapToMovieCorrectlyMapsMovieDtoToMovie() {
        UUID uuid = UUID.randomUUID();
        String name = "Movie";
        String genre = "Science fiction";
        int releaseYear = 2024;
        double rating = 8.5;

        MovieDto movieDto = new MovieDto(uuid, name, genre, releaseYear, rating);
        Movie movie = MovieDto.map(movieDto);

        assertEquals(uuid, movie.getId());
        assertEquals(name, movie.getTitle());
        assertEquals(genre, movie.getGenre());
        assertEquals(releaseYear, movie.getReleaseYear());
        assertEquals(rating, movie.getRating(), 0.001);
    }

    @Test
    @DisplayName("Two instances with the same UUID should return true")
    void twoInstancesWithSameUuidShouldReturnTrue() {
        UUID uuid = UUID.randomUUID();
        MovieDto movieDto1 = new MovieDto(uuid, "Shrek", "Action", 2001, 7.9);
        MovieDto movieDto2 = new MovieDto(uuid, "Shrek", "Action", 2001, 7.9);

        assertEquals(movieDto1, movieDto2);
    }

    @Test
    @DisplayName("Two instances with different UUID should return true")
    void twoInstancesWithDifferentUuidShouldReturnTrue() {
        MovieDto movieDto1 = new MovieDto(UUID.randomUUID(), "Shrek", "Action", 2001, 7.9);
        MovieDto movieDto2 = new MovieDto(UUID.randomUUID(), "Shrek", "Action", 2001, 7.9);

        assertNotEquals(movieDto1, movieDto2);
    }
}
