package com.example.rms.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.rms.dto.CustomerDto;
import com.example.rms.entities.Customer;

@Mapper
public interface CustomerMapper {
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "table", ignore = true)
    CustomerDto customerToCustomerDto(Customer customer);

    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "table", ignore = true)
    Customer customerDtoToCustomer(CustomerDto customerDto);
}
