package com.example.rms.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @GetMapping("{id}")
    public MenuDto getMenuItemById(@PathVariable Long id) {
        return menuService.getMenuItemById(id).orElseThrow();
    }

    @PostMapping
    public ResponseEntity<MenuDto> newMenuItem(@RequestBody MenuDto newMenuItem) {
        newMenuItem.setId(null);
        MenuDto saved = menuService.saveMenuItem(newMenuItem);
        return ResponseEntity
                .created(URI.create("/api/v1/customer/" + saved.getId()))
                .body(saved);  
    }
}
