package com.example.rms.services;

import java.util.List;
import java.util.Optional;

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
    
    @Override
    public Optional<OrderDto> getOrderById(Long id) {
        return Optional.ofNullable(
            orderMapper.orderToOrderDto(orderRepository.findById(id).orElse(null))
        );
    }

    @Override
    public OrderDto saveOrder(OrderDto newOrder) {
        return orderMapper.orderToOrderDto(orderRepository.save(orderMapper.orderDtoToOrder(newOrder)));
    }
}
