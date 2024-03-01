package org.example.jakarta_labb.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.jakarta_labb.entity.Movie;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class MovieRepository {

    @PersistenceContext(unitName = "postgresql")
    private EntityManager entityManager;

    public List<Movie> findAllMovies() {
        return entityManager
            .createQuery("SELECT m FROM Movie m", Movie.class)
            .getResultList();
    }

    public Movie findMovieById(UUID id) {
        return entityManager.find(Movie.class, id);
    }

    @Transactional
    public Movie saveMovie(Movie movie) {
        entityManager.persist(movie);
        return movie;
    }
    @Transactional
    public Movie updateMovie(Movie movie) {
        return entityManager.merge(movie);
    }

    @Transactional
    public void deleteMovie(Movie movie) {
        entityManager.remove(entityManager.contains(movie) ? movie : entityManager.merge(movie));
    }
}
