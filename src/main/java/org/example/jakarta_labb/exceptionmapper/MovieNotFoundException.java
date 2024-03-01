package org.example.jakarta_labb.exceptionmapper;

public class MovieNotFoundException extends MovieException {
    public MovieNotFoundException(String message) {
        super(message);
    }
}
