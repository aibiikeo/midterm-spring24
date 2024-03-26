package com.example.rms.mappers;

import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.rms.dto.MenuDto;
import com.example.rms.dto.OrderDto;
import com.example.rms.entities.Menu;
import com.example.rms.entities.Order;

@Mapper
public interface MenuMapper {
    Menu menuDtoToMenu(MenuDto menuDto);
    MenuDto menuToMenuDto(Menu menu);   
    
    @Mapping(target = "orders", source = "value")
    Set<OrderDto> map(Set<Order> value);
}
