package com.example.rms.mappers;

import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.rms.dto.MenuDto;
import com.example.rms.dto.OrderDto;
import com.example.rms.entities.Menu;
import com.example.rms.entities.Order;

@Mapper
public interface OrderMapper {
    OrderDto orderToOrderDto(Order order);
    Order orderDtoToOrder(OrderDto orderDto);

    @Mapping(target = "menuItems", source = "value")
    Set<MenuDto> map(Set<Menu> value);
}
