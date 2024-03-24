package com.example.rms.repositoryTests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.rms.entities.Order;
import com.example.rms.repositories.OrderRepository;

@SpringBootTest
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testFindByStatus() {
        List<Order> orders = orderRepository.findByStatus("new");
        assertNotNull(orders);
    }
}
