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

    @ManyToOne
    private Screen screen;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> Features;
}
