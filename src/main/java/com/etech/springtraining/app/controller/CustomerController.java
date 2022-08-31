package com.etech.springtraining.app.controller;

import com.etech.springtraining.app.dto.CustomerDto;
import com.etech.springtraining.app.dto.CustomerSaveRequestDto;
import com.etech.springtraining.app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping()
    public ResponseEntity save(@Valid @RequestBody CustomerSaveRequestDto customerSaveRequestDto){
        CustomerDto customerDto = customerService.save(customerSaveRequestDto);

        return ResponseEntity.ok(customerDto);
    }

    @GetMapping()
    public ResponseEntity findAll(){
        List<CustomerDto> customerDtoList = customerService.findAll();

        return ResponseEntity.ok(customerDtoList);
    }





}
