package com.example.rms.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.rms.entities.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{
    List<Order> findByStatus(String status);
}
