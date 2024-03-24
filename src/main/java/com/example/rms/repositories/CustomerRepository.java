package com.example.rms.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.rms.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
    
}
