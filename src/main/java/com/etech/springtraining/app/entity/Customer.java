package com.etech.springtraining.app.entity;

import com.etech.springtraining.app.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Boolean working;

    private String aboutMe;

    private Integer age;

    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
}
