package com.example.rms.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.rms.dto.CustomerDto;
import com.example.rms.mappers.CustomerMapper;
import com.example.rms.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .toList();
    }
}