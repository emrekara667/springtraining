package com.etech.springtraining.app.converter;

import com.etech.springtraining.app.dto.CustomerDto;
import com.etech.springtraining.app.dto.CustomerFullSaveRequestDto;
import com.etech.springtraining.app.dto.CustomerSaveRequestDto;
import com.etech.springtraining.app.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer covertToCustomer(CustomerSaveRequestDto customerSaveRequestDto);

    Customer convertToCustomer(CustomerFullSaveRequestDto customerFullSaveRequestDto);

    CustomerDto convertToCustomerDto(Customer customer);

    List<CustomerDto> convertToCustomerDtoList(List<Customer> customerList);
}
