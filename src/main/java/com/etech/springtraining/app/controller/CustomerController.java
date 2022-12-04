package com.etech.springtraining.app.controller;

import com.etech.springtraining.app.dto.CustomerDto;
import com.etech.springtraining.app.dto.CustomerFullSaveRequestDto;
import com.etech.springtraining.app.dto.CustomerSaveRequestDto;
import com.etech.springtraining.app.entity.Customer;
import com.etech.springtraining.app.gen.filter.GeneralFilter;
import com.etech.springtraining.app.service.CustomerService;
import com.etech.springtraining.app.service.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
@Validated
@Slf4j
public class CustomerController {

    private final CustomerService customerService;
    private final TransferService transferService;

    @PostMapping()
    public ResponseEntity save(@Valid @RequestBody CustomerSaveRequestDto customerSaveRequestDto) {
        CustomerDto customerDto = customerService.save(customerSaveRequestDto);

        return ResponseEntity.ok(customerDto);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll() {
        log.info("Reached controller");


        String s = GeneralFilter.rGetter();
        log.error(s);


        List<CustomerDto> customerDtoList = customerService.findAll();
        Long id = 1L;
        CustomerDto customerDto = CustomerDto.builder().id(id).name("emre").aboutMe("engineer").build();
        customerDtoList.add(customerDto);

        return ResponseEntity.ok(customerDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable @Min(1) Long id) {
        CustomerDto customerDto = customerService.findById(id);

        return ResponseEntity.ok(customerDto);
    }

    @PostMapping("/rest")
    public ResponseEntity<Customer> saveRest(@Valid @RequestBody CustomerSaveRequestDto customerSaveRequestDto) throws JSONException {
        Customer response = transferService.save(customerSaveRequestDto);
        return ResponseEntity.ok(response);
    }
}
