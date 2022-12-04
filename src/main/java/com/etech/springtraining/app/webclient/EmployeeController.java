package com.etech.springtraining.app.webclient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class EmployeeController {
    
    private final EmployeeRestClient employeeRestClient;
    
    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody Employee employee){
        Employee response = employeeRestClient.addNewEmployee(employee);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id){
        Employee employee = employeeRestClient.retrieveEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    
}
