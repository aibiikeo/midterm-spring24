package com.example.rms.controllers;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.rms.dto.OrderDto;
import com.example.rms.services.OrderService;

import jakarta.persistence.EntityNotFoundException;
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

    @PutMapping("{id}")
    public ResponseEntity<OrderDto> putOrder(@PathVariable Long id, @RequestBody OrderDto putOrder) {
        putOrder.setId(id);
        OrderDto updated = orderService.putOrder(putOrder);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("{id}")
    public ResponseEntity<OrderDto> patchOrder(@PathVariable Long id, @RequestBody Map<String, Object> patchOrder) {
        OrderDto existing = orderService.getOrderById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        patchOrder.forEach((key, value) -> {
            switch (key) {
                case "status":
                    existing.setStatus((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field in patch request: " + key);
            }
        });

        OrderDto updated = orderService.patchOrder(existing);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
