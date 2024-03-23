package com.example.rms.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.rms.entities.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
    
}
