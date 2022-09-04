package com.etech.springtraining.app.controller;

import com.etech.springtraining.app.dto.CustomerDto;
import com.etech.springtraining.app.dto.CustomerFullSaveRequestDto;
import com.etech.springtraining.app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping()
    public ResponseEntity save(@Valid @RequestBody CustomerFullSaveRequestDto customerFullSaveRequestDto) {
        CustomerDto customerDto = customerService.save(customerFullSaveRequestDto);

        return ResponseEntity.ok(customerDto);
    }

    @GetMapping()
    public ResponseEntity<Object> findAll() {
        List<CustomerDto> customerDtoList = customerService.findAll();

        return ResponseEntity.ok(customerDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable @Min(1) Long id) {
        CustomerDto customerDto = customerService.findById(id);

        return ResponseEntity.ok(customerDto);
    }
}
