package org.example.jakarta_labb.entity;

import org.example.jakarta_labb.entity.Movie;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void testIdGetterAndSetter() {
        Movie movie = new Movie();
        UUID uuid = UUID.randomUUID();
        movie.setId(uuid);
        assertEquals(uuid, movie.getId());
    }

    @Test
    void testTitleGetterAndSetter() {
        Movie movie = new Movie();
        movie.setTitle("Inception");
        assertEquals("Inception", movie.getTitle());
    }

    @Test
    void testReleaseYearGetterAndSetter() {
        Movie movie = new Movie();
        movie.setReleaseYear(2010);
        assertEquals(2010, movie.getReleaseYear());
    }

    @Test
    void testGenreGetterAndSetter() {
        Movie movie = new Movie();
        movie.setGenre("Sci-Fi");
        assertEquals("Sci-Fi", movie.getGenre());
    }

    @Test
    void testRatingGetterAndSetter() {
        Movie movie = new Movie();
        movie.setRating(4.5);
        assertEquals(4.5, movie.getRating(), 0.001);
    }
}
