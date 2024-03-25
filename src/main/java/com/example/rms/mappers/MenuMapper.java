package com.example.rms.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.rms.dto.MenuDto;
import com.example.rms.entities.Menu;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    @Mapping(target = "orders", ignore = true)
    Menu menuDtoToMenu(MenuDto menuDto);
    
    @Mapping(target = "orders", ignore = true)
    MenuDto menuToMenuDto(Menu menu);   
        
}
