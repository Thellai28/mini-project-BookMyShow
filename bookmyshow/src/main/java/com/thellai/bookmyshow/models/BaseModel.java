package com.thellai.bookmyshow.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter // Lomobok comments :
@Setter
@MappedSuperclass // Meaning this is the super class for many models.
public class BaseModel {
    @Id // To mention,this attribute is the primary key for this table :
    @GeneratedValue ( strategy = GenerationType.IDENTITY) // To identigy the primary key will increment automatically
    private long id;
    private Date createdAt;
    private Date lastModifiedAt;
}
