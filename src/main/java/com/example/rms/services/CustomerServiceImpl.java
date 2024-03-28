package com.example.rms.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.rms.dto.CustomerDto;
import com.example.rms.entities.Customer;
import com.example.rms.mappers.CustomerMapper;
import com.example.rms.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .toList();
    }

    @Override
    public Optional<CustomerDto> getCustomerById(Long id) {
        return Optional.ofNullable(
                customerMapper.customerToCustomerDto(customerRepository.findById(id).orElse(null))
        );
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto newCustomer) {
        return customerMapper.customerToCustomerDto(customerRepository.save(customerMapper.customerDtoToCustomer(newCustomer)));
    }

    @Override
    public CustomerDto putCustomer(CustomerDto putCustomer) {
        Customer existing = customerRepository.findById(putCustomer.getId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        existing.setCustomer(putCustomer.getCustomer());

        Customer updated = customerRepository.save(existing);
        return customerMapper.customerToCustomerDto(updated);
    }

    @Override
    public CustomerDto patchCustomer(CustomerDto patchCustomer) {
        Customer existing = customerRepository.findById(patchCustomer.getId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        if (patchCustomer.getCustomer() != null) {
            existing.setCustomer(patchCustomer.getCustomer());
        }

        Customer updated = customerRepository.save(existing);
        return customerMapper.customerToCustomerDto(updated);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customerToDelete = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        customerToDelete.getOrders().forEach(order -> order.setCustomer(null));
        customerRepository.deleteById(id);
    }

}
