package org.example.jakarta_labb.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.MediaType;
import org.example.jakarta_labb.dto.MovieDto;
import org.example.jakarta_labb.dto.Movies;
import org.example.jakarta_labb.service.MovieService;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.spi.Dispatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class MovieResourceTest {

    @Mock
    MovieService movieService;
    ObjectMapper objectMapper;

    Dispatcher dispatcher;


    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        dispatcher = MockDispatcherFactory.createDispatcher();
        var resource = new MovieResource(movieService);
        dispatcher.getRegistry().addSingletonResource(resource);
    }

    @Test
    public void moviesReturnsWithStatus200() throws Exception {
        when(movieService.getAllMovies()).thenReturn(new Movies(List.of(), LocalDateTime.now()));

        MockHttpRequest request = MockHttpRequest.get("/movies");
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        assertEquals(200, response.getStatus());
        assertEquals("{\"movieDtos\":[],\"updated\"}", response.getContentAsString());
    }


    @Test
    @DisplayName("Get movie by ID returns with status 200")
    public void getMovieByIdReturnsWithStatus200() throws Exception {
        UUID testUUID = UUID.randomUUID();
        MovieDto movieDto = new MovieDto(testUUID, "I am dead ", "Action", 2021, 8.5);

        when(movieService.getMovieById(any(UUID.class))).thenReturn(movieDto);

        MockHttpRequest request = MockHttpRequest.get("/movies/" + testUUID.toString());
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        assertEquals(200, response.getStatus());
        assertEquals(objectMapper.writeValueAsString(movieDto), response.getContentAsString());
    }

    @Test
    @DisplayName("Add movie with POST returns 200")
    void addMovieReturnsStatus200() throws URISyntaxException, UnsupportedEncodingException, JsonProcessingException {
        MovieDto movieDto = new MovieDto(UUID.randomUUID(), "Dead and forgotten ", "Drama", 2021, 9.0);

        when(movieService.addMovie(any(MovieDto.class))).thenReturn(movieDto);

        MockHttpRequest request = MockHttpRequest.post("/movies");
        request.contentType(MediaType.APPLICATION_JSON);
        request.content(objectMapper.writeValueAsBytes(movieDto));
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        assertEquals(200, response.getStatus());
        assertEquals(objectMapper.writeValueAsString(movieDto), response.getContentAsString());
    }

    @Test
    @DisplayName("Update movie with PUT returns 200")
    void updateMovieReturnsStatus200() throws URISyntaxException, UnsupportedEncodingException, JsonProcessingException {
        UUID testUUID = UUID.randomUUID();
        MovieDto movieDto = new MovieDto(testUUID, "Lalaland", "Comedy", 2022, 7.5);

        when(movieService.updateMovie(eq(testUUID), any(MovieDto.class))).thenReturn(movieDto);

        MockHttpRequest request = MockHttpRequest.put("/movies/" + testUUID.toString());
        request.contentType(MediaType.APPLICATION_JSON);
        request.content(objectMapper.writeValueAsBytes(movieDto));
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        assertEquals(200, response.getStatus());
        assertEquals(objectMapper.writeValueAsString(movieDto), response.getContentAsString());
    }

    @Test
    @DisplayName("Delete movie with DELETE returns 204")
    void deleteMovieReturnsStatus204() throws URISyntaxException {
        UUID testUUID = UUID.randomUUID();

        Mockito.doNothing().when(movieService).deleteMovie(any(UUID.class));

        MockHttpRequest request = MockHttpRequest.delete("/movies/" + testUUID.toString());
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        assertEquals(204, response.getStatus());
    }

    @Test
    @DisplayName("getAllMovies throws exception")
    public void getAllMoviesThrowsException() throws Exception {
        when(movieService.getAllMovies()).thenThrow(new RuntimeException("Database error"));

        MockHttpRequest request = MockHttpRequest.get("/movies");
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        assertEquals(500, response.getStatus());
    }

}
