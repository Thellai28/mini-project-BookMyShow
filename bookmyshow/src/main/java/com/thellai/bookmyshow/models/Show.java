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
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private  Movie movie;

    private Date startTime;

    private Date endTime;
    // This start and end time is specific to show, and it will be entered by the admins, data base don't have to
    // take care of them, so we don't use @CreatedDate :

    @ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
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


/*
1. **CascadeType.PERSIST:**
   - Associated with the `EntityManager.persist()` operation.
   - Saves a new entity and its associated entities.
   - Equivalent to the SQL `INSERT` operation.

2. **CascadeType.MERGE:**
   - Associated with the `EntityManager.merge()` operation.
   - Updates the state of a detached entity and its associated entities to the database.
   - Equivalent to the SQL `UPDATE` operation.

3. **CascadeType.REMOVE:**
   - Associated with the `EntityManager.remove()` operation.
   - Removes an entity and its associated entities from the database.
   - Equivalent to the SQL `DELETE` operation.

4. **CascadeType.REFRESH:**
   - Associated with the `EntityManager.refresh()` operation.
   - Refreshes the state of an entity and its associated entities from the database.
   - Equivalent to reloading data from the database.

5. **CascadeType.ALL:**
   - Represents a combination of all cascade types (`PERSIST`, `MERGE`, `REMOVE`, `REFRESH`).
   - Cascades all operations: save, update, remove, and refresh.

6. **CascadeType.DETACH:**
   - Detaches an entity and its associated entities from the persistence context.
   - Useful when you want to manage the lifecycle of entities explicitly.

These `CascadeType` values allow you to specify how certain operations
 on the owning side of a JPA relationship should be propagated to the associated entities on
 the inverse side. The appropriate choice depends on your specific use case and how you want
 changes to be managed and cascaded across entities.
*/
