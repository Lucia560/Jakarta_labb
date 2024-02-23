package org.example.jakarta_labb;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {

    @Test
    public void testIdGetterAndSetter() {
        Movie movie = new Movie();
        movie.setId(1L);
        assertEquals(1L, movie.getId());
    }

    @Test
    public void testTitleGetterAndSetter() {
        Movie movie = new Movie();
        movie.setTitle("Inception");
        assertEquals("Inception", movie.getTitle());
    }

    @Test
    public void testReleaseYearGetterAndSetter() {
        Movie movie = new Movie();
        movie.setReleaseYear(2010);
        assertEquals(2010, movie.getReleaseYear());
    }

    @Test
    public void testGenreGetterAndSetter() {
        Movie movie = new Movie();
        movie.setGenre("Sci-Fi");
        assertEquals("Sci-Fi", movie.getGenre());
    }

    @Test
    public void testRatingGetterAndSetter() {
        Movie movie = new Movie();
        movie.setRating(4.5);
        assertEquals(4.5, movie.getRating(), 0.001);
    }
}
