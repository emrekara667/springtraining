package com.etech.springtraining.app.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class CustomerSaveRequestDto {

    @NotEmpty(message = "Cannot be null")
    @Size(min = 1, max = 19, message = " sholud enter valid value ")
    private String name;

    private String surname;

    private Long password;
}
