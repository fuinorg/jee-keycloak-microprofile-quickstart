package org.fuin.examples.jkmq.service;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

/**
 * Returns person related data.
 */
@Repository(forEntity = Person.class)
public interface PersonRepository extends EntityRepository<Person, Long> {

}