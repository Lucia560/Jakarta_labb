package org.example.jakarta_labb.resource;

import org.example.jakarta_labb.dto.MovieDto;
import org.example.jakarta_labb.entity.Movie;
import org.example.jakarta_labb.repository.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
public class MovieResourceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieResource movieResource;

    private MovieDto movieDto;
    private Movie movie;

    @BeforeEach
    void setUp() {
        UUID movieId = UUID.randomUUID();
        movieDto = new MovieDto(movieId, "The Shawshank Redemption", "Drama", 1994, 9.3);

        movie = new Movie();
        movie.setId(movieId);
        movie.setTitle("The Shawshank Redemption");
        movie.setGenre("Drama");
        movie.setReleaseYear(1994);
        movie.setRating(9.3);
    }



    @Test
    @DisplayName("Return all movies if the list is not empty")
    void returnAllMoviesIfTheListIsNotEmpty() {
        when(movieRepository.findAllMovies()).thenReturn(List.of(movie));
        List<MovieDto> movieDtos = movieResource.getAllMovies().movieDtos();
        assertNotNull(movieDtos);
        assertEquals(1, movieDtos.size());
        assertEquals(movieDto.name(), movieDtos.get(0).name());

    }

    @Test
    @DisplayName("Get movies by id")
    void getMovieById() {
        when(movieRepository.findMovieById(movieDto.uuid())).thenReturn(movie);
        MovieDto resultDto = movieResource.getMovieById(movieDto.uuid());
        assertNotNull(resultDto);
        assertEquals(movieDto.name(), resultDto.name());

    }

    @Test
    @DisplayName("Add movie to the list ")
    void addMovieToTheList() {
        when(movieRepository.saveMovie(any(Movie.class))).thenReturn(movie);
        MovieDto savedDto = movieResource.addMovie(movieDto);
        assertNotNull(savedDto);
        assertEquals(movieDto.name(), savedDto.name());

    }

    @Test
    @DisplayName("Update movie and return it ")
    void updateMovieAndReturnIt() {
        when(movieRepository.findMovieById(movieDto.uuid())).thenReturn(movie);
        when(movieRepository.updateMovie(any(Movie.class))).thenReturn(movie);
        MovieDto updatedDto = movieResource.updateMovie(movieDto.uuid(), movieDto);
        assertNotNull(updatedDto);
        assertEquals(movieDto.name(), updatedDto.name());

    }

    @Test
    @DisplayName("Delete movie ")
    void deleteMovie() {
        when(movieRepository.findMovieById(movieDto.uuid())).thenReturn(movie);
        doNothing().when(movieRepository).deleteMovie(any(Movie.class));
        assertDoesNotThrow(() -> movieResource.deleteMovie(movieDto.uuid()));
    }
}
