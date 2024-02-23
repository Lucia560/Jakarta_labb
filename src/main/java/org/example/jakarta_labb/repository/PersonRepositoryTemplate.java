package org.example.jakarta_labb.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.jakarta_labb.entity.PersonTemplate;

import java.io.Serializable;
import java.util.List;

@ApplicationScoped
public class PersonRepositoryTemplate implements Serializable {

    @PersistenceContext(unitName = "mysql")
    EntityManager entityManager;

    public List<PersonTemplate> all() {
        return entityManager
            .createQuery("select p from PersonTemplate p", PersonTemplate.class)
            .getResultList();
    }

    @Transactional
    public PersonTemplate add(PersonTemplate person) {
        entityManager.persist(person);
        return person;
    }

    public PersonTemplate findById(long id) {
        return entityManager.find(PersonTemplate.class, id);
    }
}
