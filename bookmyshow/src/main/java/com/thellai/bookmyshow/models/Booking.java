package com.thellai.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter // Lomobok comments :
@Setter
@Entity
public class Booking extends BaseModel{
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "booking_show_seat",
            joinColumns = @JoinColumn( name = "booking_id" ),
            inverseJoinColumns = @JoinColumn( name = "show_seat_id")
            // DOUBT : Can i give any name other than inverseJoinColumns?
    )

    private List<ShowSeat> showSeat;
    /*  Since show seat status have CANCELLED option, after booking the seat, lets assume, the user
        cancels that particular seat, then other user will book that seat, in this scenario,
        once the user cancels the seat, we don't destroy that object from the showSeat table, we have to use the same object
        to be referred by another booking object or in other ticket.

        And when the booking is cancelled,
        That particular data is not also, removed from the booking table, because of this fact, one show seat
        can exist in multiple bookings.
    */



    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private User user;

    private Date bookedAt;

    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    // Doubt : why its given in curey braces, and without braces inline 51 or over payment.
    private Show show;

    private int amount;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Payment> payments;

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
}

/*
    @JoinTable: This annotation is used to specify the details of the join table that will be used
    to represent the many-to-many relationship in the database.name = "booking_show_seat": Specifies
     the name of the join table in the database. In this case, the join table is
     named "booking_show_seat".
     joinColumns = @JoinColumn(name = "booking_id"): Specifies the column in the join table
     that references the Booking entity. The name attribute specifies the name of the column,
     and it is set to "booking_id". This column will contain foreign key references to the
     Booking entity.
     inverseJoinColumns = @JoinColumn(name = "show_seat_id"): Specifies the column in the
     join table that references the ShowSeat entity. The name attribute specifies the name of
     the column, and it is set to "show_seat_id". This column will contain foreign key
     references to the ShowSeat entity.

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