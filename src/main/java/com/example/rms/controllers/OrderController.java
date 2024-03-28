package com.example.rms.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public ResponseEntity<OrderDto> newOrder(@RequestBody OrderDto newOrder) {
        newOrder.setId(null);
        OrderDto saved = orderService.saveOrder(newOrder);
        return ResponseEntity
                .created(URI.create("/api/v1/customer/" + saved.getId()))
                .body(saved);  
    }
}
