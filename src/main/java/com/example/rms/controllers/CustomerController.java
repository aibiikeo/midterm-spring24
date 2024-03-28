package com.example.rms.controllers;

import java.net.URI;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.rms.dto.CustomerDto;
import com.example.rms.services.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer/")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping  
    public List<CustomerDto> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("{id}")
    public CustomerDto getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id).orElseThrow();
    }
    
    @PostMapping
    public ResponseEntity<CustomerDto> newCustomer(@RequestBody CustomerDto newCustomer) {
        newCustomer.setId(null);
        CustomerDto saved = customerService.saveCustomer(newCustomer);
        return ResponseEntity
                .created(URI.create("/api/v1/customer/" + saved.getId()))
                .body(saved);  
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDto> putCustomer(@PathVariable Long id, @RequestBody CustomerDto putCustomer) {
        putCustomer.setId(id);
        CustomerDto updated = customerService.putCustomer(putCustomer);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("{id}")
    public ResponseEntity<CustomerDto> patchCustomer(@PathVariable Long id, @RequestBody Map<String, Object> patchCustomer) {
        CustomerDto existing = customerService.getCustomerById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        patchCustomer.forEach((key, value) -> {
            switch (key) {
                case "customer":
                    existing.setCustomer((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field in patch request: " + key);
            }
        });

        CustomerDto updated = customerService.patchCustomer(existing);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}	