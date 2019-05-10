package org.fuin.examples.jkmq.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a person.
 */
@Entity
@Table(name = "PERSON")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "person")
public class Person {

    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    @XmlElement(name = "id")
    private Long id;

    @Column(name = "NAME", nullable = false, length = 100, updatable = true)
    @NotNull
    @XmlElement(name = "name")
    private String name;

    /**
     * JAX-B constructor.
     */
    protected Person() {
        super();
    }

    /**
     * Constructor with all data.
     * 
     * @param id
     *            Unique identifier or <code>null</code> for a new person.
     * @param name
     *            Name of the person
     */
    public Person(final Long id, @NotNull final String name) {
        super();
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the unique identifier.
     * 
     * @return Person ID.
     */    
    public Long getId() {
        return id;
    }

    /**
     * Returns the name of the person to create.
     * 
     * @return the name
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     * 
     * @param name
     *            Name to set.
     */
    public void setName(@NotNull final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + "]";
    }

}
