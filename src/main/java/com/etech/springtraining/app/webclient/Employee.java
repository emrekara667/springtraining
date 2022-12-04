package com.etech.springtraining.app.webclient;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    private String gender;

    private String role;
}


