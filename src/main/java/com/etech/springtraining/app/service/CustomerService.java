package com.etech.springtraining.app.service;

import com.etech.springtraining.app.converter.CustomerMapper;
import com.etech.springtraining.app.dao.CustomerDao;
import com.etech.springtraining.app.dto.CustomerDto;
import com.etech.springtraining.app.dto.CustomerFullSaveRequestDto;
import com.etech.springtraining.app.dto.CustomerSaveRequestDto;
import com.etech.springtraining.app.entity.Customer;
import com.etech.springtraining.app.exception.BadRequestException;
import com.etech.springtraining.app.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerDao customerDao;


    public CustomerDto save(CustomerSaveRequestDto customerSaveRequestDto) {

     /*   if(customerSaveRequestDto.getName() == null){
            throw new NotFoundException("not found");
        }
        if(StringUtils.isEmpty(customerSaveRequestDto.getName())){
            throw new RuntimeException("run time");
        }*/

        Customer customer = CustomerMapper.INSTANCE.covertToCustomer(customerSaveRequestDto);

        if(customerDao.existsByName(customerSaveRequestDto.getName())){
            throw new BadRequestException("Entity name already exist");
        }

        customer = customerDao.save(customer);

        return CustomerMapper.INSTANCE.convertToCustomerDto(customer);
    }

    public List<CustomerDto> findAll() {

        List<Customer> customerList = customerDao.findAll();

        return CustomerMapper.INSTANCE.convertToCustomerDtoList(customerList);
    }

    public CustomerDto findById(Long id) {

        Customer customer = findByIdWithControl(id);

        CustomerDto customerDto = CustomerMapper.INSTANCE.convertToCustomerDto(customer);

        return customerDto;
    }

    private Customer findByIdWithControl(Long id) {
        Optional<Customer> customerOptional = customerDao.findById(id);

        return customerDao.findById(id).orElseThrow(() -> {
            log.error("Unresolved id");
            return new NotFoundException("test");
        });
    }
}
