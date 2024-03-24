package com.example.rms.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.rms.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{
    List<Customer> findByCustomer(String customer);
}
