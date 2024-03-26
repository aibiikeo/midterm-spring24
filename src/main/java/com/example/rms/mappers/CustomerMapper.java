package com.example.rms.mappers;

import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.rms.dto.CustomerDto;
import com.example.rms.dto.OrderDto;
import com.example.rms.entities.Customer;
import com.example.rms.entities.Order;

@Mapper
public interface CustomerMapper {
    CustomerDto customerToCustomerDto(Customer customer);
    Customer customerDtoToCustomer(CustomerDto customerDto);

    @Mapping(target = "orders", source = "value")
    Set<OrderDto> map(Set<Order> value);
}
