package org.example.jakarta_labb.exceptionmapper;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieExceptionMapperTest {
    private final MovieExceptionMapper exceptionMapper = new MovieExceptionMapper();
    @Test
    void testMovieNotFoundException() {
        MovieNotFoundException exception = new MovieNotFoundException("Movie not found");
        Response response = exceptionMapper.toResponse(exception);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        assertEquals("Movie not found", response.getEntity());
    }

    @Test
    void testMovieValidationException() {
        MovieValidationException exception = new MovieValidationException("Invalid input");
        Response response = exceptionMapper.toResponse(exception);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals("Invalid input", response.getEntity());
    }

    @Test
    void testMovieExceptionForInternalServerError() {
        MovieException exception = new MovieException("Some other exception");
        Response response = exceptionMapper.toResponse(exception);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("Internal server error", response.getEntity());
    }
}
