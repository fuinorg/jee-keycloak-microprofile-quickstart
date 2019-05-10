package org.fuin.examples.jkmq.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.opentracing.Traced;

@Traced
@Stateless
@Path("/persons")
public class PersonResource {

    @Inject
    private PersonRepository repo;

    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @TransactionAttribute(TransactionAttributeType.NEVER)
    @RolesAllowed("user")
    @Timeout(1000)
    @Fallback(fallbackMethod = "noPersonsFound")
    public List<Person> getPersons() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {//NOSONAR
            throw new RuntimeException(ex);
        }
        return repo.findAll();
    }

    private List<Person> noPersonsFound() {
        final List<Person> persons = new ArrayList<>();
        persons.add(new Person(0L, "Joe Black"));
        return persons;
    }    
    
    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @TransactionAttribute(TransactionAttributeType.NEVER)
    @RolesAllowed("user")
    public Person getPerson(@PathParam("id") @NotNull Long id) {
        final Person person = repo.findBy(id);
        if (person == null) {
            throw new PersonNotFoundException(id);
        }
        return person;
    }

}
