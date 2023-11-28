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
    @ManyToMany
    /*  Since show seat status have CANCELLED option, after booking the seat, lets assume, the user
        cancels that particular seat, then other user will book that seat, in this scenario,
        once the user cancels the seat, we don't destroy that object from the showSeat table, we have to use the same object
        to be referred by another booking object or in other ticket.

        And when the booking is cancelled,
        That particular data is not also, removed from the booking table, because of this fact, one show seat
        can exist in multiple bookings.
    */
    private List<ShowSeat> showSeat;
    @ManyToOne
    private User user;
    private Date bookedAt;
    @ManyToOne
    private Show show;

    private int amount;

    @OneToMany
    private List<Payment> payments;

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
}
