package com.example.rms.controllers;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public CustomerDto getCustomerById(@Validated @PathVariable Long id) {
        return customerService.getCustomerById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Customer with id:%d is not found", id)));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> postCustomer(@Validated @RequestBody CustomerDto newCustomer) {
        newCustomer.setId(null);
        CustomerDto saved = customerService.saveCustomer(newCustomer);
        return ResponseEntity
                .created(URI.create("/api/v1/customer/" + saved.getId()))
                .body(saved);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDto> putCustomer(@PathVariable Long id,@Validated @RequestBody CustomerDto putCustomer) {
        putCustomer.setId(id);
        CustomerDto updated = customerService.putCustomer(putCustomer);
        return ResponseEntity.ok(updated);
    }

//    @PatchMapping("{id}")
//    public ResponseEntity<CustomerDto> patchCustomer(@PathVariable Long id,@Validated @RequestBody Map<String, Object> patchCustomer) {
//        CustomerDto existing = customerService.getCustomerById(id)
//                .orElseThrow(() -> new NotFoundException(String.format("Customer with id:%d is not found", id)));
//
//        patchCustomer.forEach((key, value) -> {
//            switch (key) {
//                case "customer":
//                    existing.setCustomer((String) value);
//                    break;
//                default:
//                    throw new IllegalArgumentException("Invalid field in patch request: " + key);
//            }
//        });
//
//        CustomerDto updated = customerService.patchCustomer(existing);
//        return ResponseEntity.ok(updated);
//    }

    @PatchMapping("{id}")
    public ResponseEntity<CustomerDto> patchCustomer(@PathVariable Long id, @Validated @RequestBody CustomerDto patchCustomer) {
        CustomerDto existing = customerService.getCustomerById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Customer with id:%d is not found", id)));

        if (patchCustomer.getCustomer() != null) {
            existing.setCustomer(patchCustomer.getCustomer());
        }

        CustomerDto updated = customerService.patchCustomer(existing);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@Validated @PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }


}	