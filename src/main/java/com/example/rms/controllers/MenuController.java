package com.example.rms.controllers;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.rms.dto.MenuDto;
import com.example.rms.services.MenuService;

import jakarta.persistence.EntityNotFoundException;
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
    public MenuDto getMenuItemById(@Validated @PathVariable Long id) {
        return menuService.getMenuItemById(id).orElseThrow(() -> new NotFoundException(String.format("Menu item with id:%d is not found", id)));
    }

    @PostMapping
    public ResponseEntity<MenuDto> postMenuItem(@Validated @RequestBody MenuDto newMenuItem) {
        newMenuItem.setId(null);
        MenuDto saved = menuService.saveMenuItem(newMenuItem);
        return ResponseEntity
                .created(URI.create("/api/v1/customer/" + saved.getId()))
                .body(saved);  
    }

    @PutMapping("{id}")
    public ResponseEntity<MenuDto> putMenuItem(@PathVariable Long id,@Validated @RequestBody MenuDto putMenuItem) {
        putMenuItem.setId(id);
        MenuDto updated = menuService.putMenuItem(putMenuItem);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("{id}")
    public ResponseEntity<MenuDto> patchMenuItem(@PathVariable Long id,@Validated @RequestBody Map<String, Object> patchMenuItem) {
        MenuDto existing = menuService.getMenuItemById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menu not found"));

        patchMenuItem.forEach((key, value) -> {
            switch (key) {
                case "name":
                    existing.setName((String) value);
                    break;
                case "description":
                    existing.setDescription((String) value);
                    break;
                case "price":
                    existing.setPrice((String) value);
                    break;
                case "category":
                    existing.setCategory((String) value);
                    break;
                case "orders":
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field in patch request: " + key);
            }
        });

        MenuDto updated = menuService.patchMenuItem(existing);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMenuItem(@Validated @PathVariable Long id) {
        menuService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}
