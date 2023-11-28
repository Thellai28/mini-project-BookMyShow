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
