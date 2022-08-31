package com.etech.springtraining.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_customer")
@Getter
@Setter
public class Customer {

    @Id
    @SequenceGenerator(name = "customer", sequenceName = "CUSTOMER_ID_SEQ")
    @GeneratedValue(generator = "customer" )
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "password")
    private Long password;
}
