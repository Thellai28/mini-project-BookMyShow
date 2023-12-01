package com.thellai.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class  User extends BaseModel{

    private String email;

    private String name;

    @OneToMany
    private List<Booking> bookings;


    private String password;

}
