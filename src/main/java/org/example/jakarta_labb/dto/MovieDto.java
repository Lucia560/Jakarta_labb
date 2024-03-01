package org.example.jakarta_labb.dto;

import jakarta.validation.constraints.*;
import org.example.jakarta_labb.entity.Movie;
import org.example.jakarta_labb.validate.CustomReleaseYear;

import java.util.UUID;

public record MovieDto(UUID uuid,
                       @NotEmpty String name,
                       @NotEmpty String genre,
                       @CustomReleaseYear(message = "Year can only be between 1900 and current") int releaseYear,
                       @DecimalMin("0.0") @DecimalMax("10.0") double rating) {

    public static Movie map(MovieDto movieDto) {
        var movie = new Movie();
        movie.setId(movieDto.uuid());
        movie.setTitle(movieDto.name());
        movie.setGenre(movieDto.genre());
        movie.setReleaseYear(movieDto.releaseYear());
        movie.setRating(movieDto.rating());
        return movie;
    }

    public static class MovieBuilder {
        private UUID uuid;
        private String name;
        private String genre;
        private int releaseYear;
        private double rating;

        public MovieBuilder uuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public MovieBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MovieBuilder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public MovieBuilder releaseYear(int releaseYear) {
            this.releaseYear = releaseYear;
            return this;
        }

        public MovieBuilder rating(double rating) {
            this.rating = rating;
            return this;
        }

        public MovieDto build() {
            return new MovieDto(uuid, name, genre, releaseYear, rating);
        }
    }

    public static MovieBuilder builder() {
        return new MovieBuilder();
    }
}
