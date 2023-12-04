package com.thellai.bookmyshow.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class  User extends BaseModel{

    @Column( nullable = false )
    private String email;



    @Column( nullable = false )
    private String name;




    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true )
    private List<Booking> bookings; // Booking table will have the userId as foreign key, not the user table
     /*
        The mappedBy attribute in the @OneToMany annotation is used to establish the owning side of a
        bidirectional relationship in JPA (Java Persistence API). In a bidirectional relationship,
        there is a owning side and an inverse side.

        NOTE : OrphanRemoval & mappedBy will be present only in OneToMany relation, they are specific
        to OneToMany relationship :
    */





    @Column( nullable = false )
    private String password;

}


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
