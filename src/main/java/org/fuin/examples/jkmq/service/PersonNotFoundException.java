package org.fuin.examples.jkmq.service;

import javax.validation.constraints.NotNull;

/**
 * A person with the given identifier does not exist.
 */
public class PersonNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * Constructor with unique identifier.
     * 
     * @param id
     *            Identifier of person not found.
     */
    public PersonNotFoundException(@NotNull final Long id) {
        super("Person not found: " + id);
        this.id = id;
    }

    /**
     * Returns the identifier of the person.
     * 
     * @return Identifier of person not found.
     */
    public Long getId() {
        return id;
    }

}
