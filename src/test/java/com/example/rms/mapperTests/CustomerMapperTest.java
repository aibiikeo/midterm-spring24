package com.example.rms.mapperTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.rms.dto.CustomerDto;
import com.example.rms.entities.Customer;
import com.example.rms.mappers.CustomerMapper;

@SpringBootTest
class CustomerMapperTest {
    @Autowired
    CustomerMapper customerMapper;

    @Test
    void customerToCustomerDto(){
        Customer customer = Customer.builder()
                .id(2L)
                .customer("Lena")
                .build();  
        CustomerDto dto = customerMapper.customerToCustomerDto(customer);
        assertNotNull(dto);
        assertEquals(2L,dto.getId());
        assertEquals("Lena", dto.getCustomer());

    }

    @Test
    void customerDtoToCustomer(){
        CustomerDto dto = CustomerDto.builder()
                .id(2L)
                .customer("Lena")
                .build();  
        Customer customer = customerMapper.customerDtoToCustomer(dto);
        assertNotNull(customer);
        assertEquals(2L,customer.getId());
        assertEquals("Lena", customer.getCustomer());
    }
}
