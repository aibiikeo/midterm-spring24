package com.example.rms.services;

import java.util.List;
import com.example.rms.dto.OrderDto;

public interface OrderService {
    List<OrderDto> getAllOrders();
}
