package com.example.rms.services;

import java.util.List;
import java.util.Optional;
import com.example.rms.dto.CustomerDto;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
    Optional<CustomerDto> getCustomerById(Long id);
}