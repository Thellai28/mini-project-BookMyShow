package com.thellai.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
@Getter
@Setter

@MappedSuperclass // Meaning this is the super class for many models.
@EntityListeners( AuditingEntityListener.class )
// Generally use in BaseModel classes, it can also be used directly on
// @Entity & @Configuration class as well depending on the requirement.
public class BaseModel {

    @Id // To mention,this attribute is the primary key for this table :
    @GeneratedValue ( strategy = GenerationType.IDENTITY) // To identity the primary key will increment automatically
    private Long id;

    @CreatedDate
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date createdAt;

    @LastModifiedDate
    @Temporal( value = TemporalType.TIMESTAMP)
    private Date lastModifiedAt;
}
    /*
        @CreatedDate:

        This annotation is often used in the context of auditing in JPA (Java Persistence API).
        It is used to mark a field in an entity class that should be automatically set to the
        current date and time when a new instance of the entity is persisted (i.e., when it is created).
        In this case, it is applied to a Date field named createdAt.
< ------------------------------------------------------------------------------------------------------------->

        @LastModifiedBy:

        This annotation is not part of the standard JPA annotations. It might be a custom annotation or
        part of a specific framework or library. It suggests that there is some mechanism in place to track the user or
        entity responsible for the last modification of the entity. Without additional context or information about the
        specific framework or library being used, it's challenging to provide more details about its behavior.

<------------------------------------------------------------------------------------------------------------------>

        @Temporal(value = TemporalType.TIMESTAMP):

        The @Temporal annotation is used to specify the type of data stored in a
        temporal (date and time) database column. In this case, it is set to TemporalType.TIMESTAMP,
        indicating that the corresponding database column should
        store both date and time information.
< ------------------------------------------------------------------------------------------------------------------->

       @MappedSuperclass

        The @MappedSuperclass annotation in Java, specifically in the context of JPA (Java Persistence API), is used to
        designate a class as a superclass whose mappings are applied to the entities that inherit from it. It allows you
         to define common fields and mappings in a superclass, and those will be inherited by its subclasses.

<-------------------------------------------------------------------------------------------------------------------->
        AuditingEntityListener.class

        This class is responsible for actively looking lifecycle changes in entities that is annotated with prePost or preUpdate
        Or any other lifecycle changes and do something depending on our logic. In this case, the modification date and
        created date will be listened by AuditingEntityListener.class. Any lifecycle change in inherited classes also
        will be listened and logic will be executed.
    */

