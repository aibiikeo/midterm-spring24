package com.example.rms.services;

import java.util.List;
import java.util.Optional;
import com.example.rms.dto.MenuDto;

public interface MenuService {
    List<MenuDto> getAllMenuItems();
    Optional<MenuDto> getMenuItemById(Long id);
    MenuDto saveMenuItem(MenuDto newMenuItem);
    MenuDto putMenuItem(MenuDto putMenuItem);
    MenuDto patchMenuItem(MenuDto patchMenuItem);
    void deleteMenuItem(Long id);
}
