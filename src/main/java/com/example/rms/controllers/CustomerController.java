package com.example.rms.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.rms.dto.CustomerDto;
import com.example.rms.services.CustomerService;
import lombok.RequiredArgsConstructor;

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
}