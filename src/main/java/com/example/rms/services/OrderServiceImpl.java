package com.example.rms.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.rms.dto.OrderDto;
import com.example.rms.mappers.OrderMapper;
import com.example.rms.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    
    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::orderToOrderDto)
                .toList();
    }
    
}
