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
}
