package org.example.jakarta_labb.exceptionmapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class MovieExceptionMapper implements ExceptionMapper<MovieException> {
    @Override
    public Response toResponse(MovieException exception) {
        if (exception instanceof MovieNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(exception.getMessage())
                .build();
        } else if (exception instanceof MovieValidationException) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Internal server error")
                .build();
        }
    }
}
