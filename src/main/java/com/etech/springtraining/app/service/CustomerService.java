package com.etech.springtraining.app.service;

import com.etech.springtraining.app.converter.CustomerMapper;
import com.etech.springtraining.app.dao.CustomerDao;
import com.etech.springtraining.app.dto.CustomerDto;
import com.etech.springtraining.app.dto.CustomerSaveRequestDto;
import com.etech.springtraining.app.entity.Customer;
import com.etech.springtraining.app.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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

        customer = customerDao.save(customer);

        return   CustomerMapper.INSTANCE.convertToCustomerDto(customer);
    }

    public List<CustomerDto> findAll() {

        List<Customer> customerList = customerDao.findAll();

        return CustomerMapper.INSTANCE.convertToCustomerDtoList(customerList);
    }
}
