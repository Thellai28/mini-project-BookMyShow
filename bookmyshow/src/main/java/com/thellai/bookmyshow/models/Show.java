package com.thellai.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity( name = "shows" ) // show is a reserved keyword in SQL, it'll throw error, if we don't change the name of the model
public class Show extends BaseModel{
    @ManyToOne
    private  Movie movie;

    private Date startTime;

    private Date endTime;
    // This start and end time is specific to show, and it will be entered by the admins, data base don't have to
    // take care of them, so we don't use @CreatedDate :

    @ManyToOne
    private Screen screen;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> Features;
}

/*
    The @ElementCollection annotation in Java, particularly in the context of JPA (Java Persistence API),
    is used to define a collection of basic or embeddable types within an entity.
    It allows you to model one-to-many relationships where the "many" side is not another entity but rather a
    collection of simple types or embeddable objects. Here's an example:


    Simple Types:

    Simple types refer to basic, non-entity Java types like String, Integer, Date, etc.
    When you use @ElementCollection with simple types, each element of the collection
    corresponds to a single, atomic value.

    Embeddable Objects:

    Embeddable objects are user-defined classes that represent a grouping of multiple attributes.
    When you use @ElementCollection with embeddable objects, each element of the collection
    is an instance of the embeddable class.

    @Embeddable
    public class Address {
        private String street;
        private String city;
        private String zipCode;

        // Constructors, getters, setters, etc.
    }
*/
