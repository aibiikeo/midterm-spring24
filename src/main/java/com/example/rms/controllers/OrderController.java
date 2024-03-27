package com.example.rms.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.rms.dto.OrderDto;
import com.example.rms.services.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order/")
public class OrderController {
    private final OrderService orderService;

    @GetMapping  
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }
    
    @GetMapping("{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id).orElseThrow();
    }
}
