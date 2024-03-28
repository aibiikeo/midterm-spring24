package com.example.rms.services;

import java.util.List;
import java.util.Optional;
import com.example.rms.dto.OrderDto;

public interface OrderService {
    List<OrderDto> getAllOrders();
    Optional<OrderDto> getOrderById(Long id);
    OrderDto saveOrder(OrderDto newOrder);
    OrderDto putOrder(OrderDto putOrder);
    OrderDto patchOrder(OrderDto patchOrder);
    void deleteOrder(Long id);
}
