package com.example.rms.mapperTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.rms.dto.OrderDto;
import com.example.rms.entities.Order;
import com.example.rms.mappers.OrderMapper;

@SpringBootTest
class OrderMapperTest {
    @Autowired
    OrderMapper orderMapper;

    @Test
    void OrderToOrderDto(){
        Order order = new Order();
        order.setId(1L);
        order.setStatus("new");
        OrderDto orderDto = orderMapper.orderToOrderDto(order);
        assertNotNull(orderDto);
        assertEquals(order.getId(), orderDto.getId());
        assertEquals(order.getStatus(), orderDto.getStatus());
    }

    @Test
    void OrderDtoToOrder(){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setStatus("new");
        Order order = orderMapper.orderDtoToOrder(orderDto);
        assertNotNull(order);
        assertEquals(orderDto.getId(), order.getId());
        assertEquals(orderDto.getStatus(), order.getStatus());
    }
}
