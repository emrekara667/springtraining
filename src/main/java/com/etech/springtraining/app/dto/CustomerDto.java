package com.etech.springtraining.app.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Data
@Builder
public class CustomerDto {

    private Long id;

    private String name;

    private String surname;
}
