package com.example.rms.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.rms.dto.OrderDto;
import com.example.rms.entities.Order;

@Mapper
public interface OrderMapper {
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "table", ignore = true)
    @Mapping(target = "menuItems", ignore = true)
    OrderDto orderToOrderDto(Order order);
    
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "table", ignore = true)
    @Mapping(target = "menuItems", ignore = true)
    Order orderDtoToOrder(OrderDto orderDto);
}
