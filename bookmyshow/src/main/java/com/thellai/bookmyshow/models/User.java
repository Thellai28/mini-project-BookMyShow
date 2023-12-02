package com.thellai.bookmyshow.models;

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

    @OneToMany
    private List<Booking> bookings; // Booking table will have the userId as foreign key, not the user table

    @Column( nullable = false )
    private String password;

}
