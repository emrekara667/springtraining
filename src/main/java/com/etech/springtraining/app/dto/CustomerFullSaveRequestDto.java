package com.etech.springtraining.app.dto;

import com.etech.springtraining.app.Gender;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class CustomerFullSaveRequestDto implements Serializable {

    @NotBlank(message = "name cannot be blank")
    private final String name;

    @NotEmpty(message = "surname cannot be empty")
    private final String surname;

    private final Long password;

    private final Gender gender;

    @AssertTrue
    private final Boolean working;

    @Size(min = 10, max = 200, message = "number of characters should be in between 10 and 200 inclusive")
    private final String aboutMe;

    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be more than 150 ")
    private final Integer age;

    @Email(message = "Email should be valid")
    private final String email;

    @Future
    private final Date dateOfBirth;
}
