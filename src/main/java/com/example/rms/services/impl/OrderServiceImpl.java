package com.example.rms.services.impl;

import java.util.List;
import java.util.Optional;

import com.example.rms.controllers.NotFoundException;
import com.example.rms.services.OrderService;
import org.springframework.stereotype.Service;
import com.example.rms.dto.OrderDto;
import com.example.rms.entities.Order;
import com.example.rms.mappers.OrderMapper;
import com.example.rms.repositories.OrderRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
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

    @Override
    public OrderDto putOrder(OrderDto putOrder) {
        Order existing = orderRepository.findById(putOrder.getId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        existing.setStatus(putOrder.getStatus());
        Order updated = orderRepository.save(existing);
        return orderMapper.orderToOrderDto(updated);
    }

    @Override
    public OrderDto patchOrder(OrderDto patchOrder) {
        Order existing = orderRepository.findById(patchOrder.getId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if (patchOrder.getStatus() != null) {
            existing.setStatus(patchOrder.getStatus());
        }

        Order updated = orderRepository.save(existing);
        return orderMapper.orderToOrderDto(updated);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Order with id:%d is not found", id)));
        orderRepository.deleteById(id);
    }

}
