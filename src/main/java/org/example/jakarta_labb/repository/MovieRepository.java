package org.example.jakarta_labb.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.jakarta_labb.entity.Movie;

import java.util.List;

@ApplicationScoped
public class MovieRepository {

    @PersistenceContext(unitName = "mysql")
    private EntityManager entityManager;

    public List<Movie> findAllMovies() {
        return entityManager
            .createQuery("SELECT m FROM Movie m", Movie.class)
            .getResultList();
    }

    @Transactional
    public Movie saveMovie(Movie movie) {
        entityManager.persist(movie);
        return movie;
    }

    public Movie findMovieById(Long id) {
        return entityManager.find(Movie.class, id);
    }

    @Transactional
    public void deleteMovie(Movie movie) {
        entityManager.remove(entityManager.contains(movie) ? movie : entityManager.merge(movie));
    }
}
