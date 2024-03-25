package com.example.rms.mapperTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.rms.dto.MenuDto;
import com.example.rms.entities.Menu;
import com.example.rms.mappers.MenuMapper;

@SpringBootTest
class MenuMapperTest {
    @Autowired
    MenuMapper menuMapper;

    @Test
    void menuToMenuDto(){
        Menu menu = Menu.builder()
                .id(12L)
                .name("Cake")
                .description("cake")
                .price("12.4$")
                .category("Dessert")
                .build();
        MenuDto dto = menuMapper.menuToMenuDto(menu);
        assertNotNull(dto);
        assertEquals(12L,dto.getId());
        assertEquals("Cake", dto.getName());
        assertEquals("cake", dto.getDescription());
        assertEquals("12.4$", dto.getPrice());
        assertEquals("Dessert", dto.getCategory());
    }

    @Test
    void menuDtoToMenu(){
        MenuDto dto = MenuDto.builder()
                .name("Cake")
                .description("cake")
                .price("12.4$")
                .category("Dessert")
                .build();
        Menu menu = menuMapper.menuDtoToMenu(dto);
        assertNotNull(menu);
        assertEquals("Cake", menu.getName());
        assertEquals("cake", menu.getDescription());
        assertEquals("12.4$", menu.getPrice());
        assertEquals("Dessert", menu.getCategory());
    }
}
