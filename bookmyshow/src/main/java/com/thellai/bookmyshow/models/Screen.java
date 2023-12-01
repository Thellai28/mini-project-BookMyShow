package com.thellai.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Screen extends BaseModel{

    private String name;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection // -> Mapping table
    private List<Feature> features;

    @OneToMany
    private List<Seat> seats;
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
