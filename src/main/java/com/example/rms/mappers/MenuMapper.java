package com.example.rms.mappers;

import org.mapstruct.Mapper;
import com.example.rms.dto.MenuDto;
import com.example.rms.dto.OrderDto;
import com.example.rms.entities.Menu;
import com.example.rms.entities.Order;

@Mapper
public interface MenuMapper {
    Menu menuDtoToMenu(MenuDto menuDto);
    MenuDto menuToMenuDto(Menu menu);

    default OrderDto orderToOrderDto(Order order) {
        if (order == null) {
            return null;
        }

        OrderDto.OrderDtoBuilder orderDto = OrderDto.builder();

        orderDto.id(order.getId());
        orderDto.status(order.getStatus());

        return orderDto.build();
    }

    default Order orderDtoToOrder(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.id( orderDto.getId() );
        order.status( orderDto.getStatus() );

        return order.build();
    }    
}
