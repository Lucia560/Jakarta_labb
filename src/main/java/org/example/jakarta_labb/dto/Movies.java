package org.example.jakarta_labb.dto;

import org.example.jakarta_labb.entity.Movie;

import java.time.LocalDateTime;
import java.util.List;

public record Movies(List<MovieDto> movieDtos, LocalDateTime updated){
    public static Movies fromMovies(List<Movie> movies){
        List<MovieDto> movieDtoList = movies.stream().map(MovieDto::fromEntity).toList();
        return new Movies(movieDtoList, LocalDateTime.now());
    }
}
