package org.example.jakarta_labb.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.jakarta_labb.dto.PersonDtoTemplate;
import org.example.jakarta_labb.dto.PersonsTemplate;
import org.example.jakarta_labb.repository.PersonRepositoryTemplate;

import java.net.URI;
import java.time.LocalDateTime;

@Path("persons")
public class PersonResourceTemplate {

    private PersonRepositoryTemplate personRepository;

    public PersonResourceTemplate() {
    }

    @Inject
    public PersonResourceTemplate(PersonRepositoryTemplate personRepository) {
        this.personRepository = personRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PersonsTemplate all() {
        return new PersonsTemplate(
            personRepository.all().stream().map(PersonDtoTemplate::map).toList(),
            LocalDateTime.now());
    }

    //Don't use primary key as id. Use nanoid or UUID
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public PersonDtoTemplate one(@PathParam("id") long id) {
        var person = personRepository.findById(id);
        if (person == null)
            throw new NotFoundException("Invalid id " + id);
        return PersonDtoTemplate.map(person);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON)
    public Response create(PersonDtoTemplate personDto) {
        //Save to database
        var p = personRepository.add(PersonDtoTemplate.map(personDto));

        return Response.created(
                //Ask Jakarta application server for hostname and url path
                URI.create("http://localhost:8080/api/persons/" + p.getId()))
            .build();
    }
}
