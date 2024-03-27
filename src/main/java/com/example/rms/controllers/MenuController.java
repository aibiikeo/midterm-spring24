package com.example.rms.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.rms.dto.MenuDto;
import com.example.rms.services.MenuService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu/")
public class MenuController {
    private final MenuService menuService;

    @GetMapping  
    public List<MenuDto> getAllMenuItems(){
        return menuService.getAllMenuItems();
    }
}
