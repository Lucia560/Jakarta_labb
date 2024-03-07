package org.example.jakarta_labb.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.jakarta_labb.dto.MovieDto;
import org.example.jakarta_labb.dto.Movies;
import org.example.jakarta_labb.service.MovieService;

import java.util.UUID;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {

   /* @Inject
    private MovieService movieService;*/

    private MovieService movieService;

    public MovieResource(){}

    @Inject
    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }


    @GET
    public Movies getAllMovies() {
        return movieService.getAllMovies();
    }

    @GET
    @Path("/{id}")
    public MovieDto getMovieById(@PathParam("id") UUID id) {
        return movieService.getMovieById(id);
    }

    @POST
    public MovieDto addMovie(MovieDto movieDto) {
        return movieService.addMovie(movieDto);
    }

    @PUT
    @Path("/{id}")
    public MovieDto updateMovie(@PathParam("id") UUID id, MovieDto movieDto) {
        return movieService.updateMovie(id, movieDto);
    }

    @DELETE
    @Path("/{id}")
    public void deleteMovie(@PathParam("id") UUID id) {
        movieService.deleteMovie(id);
    }
}
