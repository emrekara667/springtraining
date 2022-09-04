package com.etech.springtraining.app.dto;

import com.etech.springtraining.app.Gender;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class CustomerDto {

    private final Long id;
    private final String name;
    private final String surname;
    private final Long password;
    private final Gender gender;
    private final Boolean working;
    private final String aboutMe;
    private final Integer age;
    private final String email;
    private final LocalDate dateOfBirth;
}
