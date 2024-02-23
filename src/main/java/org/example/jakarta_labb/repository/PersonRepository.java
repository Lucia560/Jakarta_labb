package org.example.jakarta_labb.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.jakarta_labb.entity.Person;

import java.io.Serializable;
import java.util.List;

@ApplicationScoped
public class PersonRepository implements Serializable {

    @PersistenceContext(unitName = "mysql")
    EntityManager entityManager;

    public List<Person> all() {
        return entityManager
            .createQuery("select p from Person p", Person.class)
            .getResultList();
    }

    @Transactional
    public Person add(Person person) {
        entityManager.persist(person);
        return person;
    }

    public Person findById(long id) {
        return entityManager.find(Person.class, id);
    }
}
