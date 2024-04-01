package com.example.rms.services;

import java.util.List;
import java.util.Optional;

import com.example.rms.controllers.NotFoundException;
import org.springframework.stereotype.Service;
import com.example.rms.dto.MenuDto;
import com.example.rms.entities.Menu;
import com.example.rms.mappers.MenuMapper;
import com.example.rms.repositories.MenuRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService{
    private final MenuMapper menuMapper;
    private final MenuRepository menuRepository;
    
    @Override
    public List<MenuDto> getAllMenuItems() {
        return menuRepository.findAll()
                .stream()
                .map(menuMapper::menuToMenuDto)
                .toList();
    }

    @Override
    public Optional<MenuDto> getMenuItemById(Long id) {
        return Optional.ofNullable(
            menuMapper.menuToMenuDto(menuRepository.findById(id).orElse(null))
        );
    }

    @Override
    public MenuDto saveMenuItem(MenuDto newMenuItem) {
        return menuMapper.menuToMenuDto(menuRepository.save(menuMapper.menuDtoToMenu(newMenuItem)));
    }
    
    @Override
    public MenuDto putMenuItem(MenuDto putMenuItem) {
        Menu existing = menuRepository.findById(putMenuItem.getId())
                .orElseThrow(() -> new EntityNotFoundException("Menu item not found"));
        existing.setName(putMenuItem.getName());
        existing.setDescription(putMenuItem.getDescription());
        existing.setCategory(putMenuItem.getCategory());
        existing.setPrice(putMenuItem.getPrice());
        Menu updated = menuRepository.save(existing);
        return menuMapper.menuToMenuDto(updated);
    }

    @Override
    public MenuDto patchMenuItem(MenuDto patchMenuItem) {
        Menu existing = menuRepository.findById(patchMenuItem.getId())
                .orElseThrow(() -> new EntityNotFoundException("Menu item not found"));

        if (patchMenuItem.getName() != null) {
            existing.setName(patchMenuItem.getName());
        }
        if (patchMenuItem.getDescription() != null) {
            existing.setDescription(patchMenuItem.getDescription());
        }
        if (patchMenuItem.getCategory() != null) {
            existing.setCategory(patchMenuItem.getCategory());
        }
        if (patchMenuItem.getPrice() != null) {
            existing.setPrice(patchMenuItem.getPrice());
        }

        Menu updated = menuRepository.save(existing);
        return menuMapper.menuToMenuDto(updated);
    }

    @Override
    public void deleteMenuItem(Long id) {
        Menu menuItemToDelete = menuRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Menu item with id:%d is not found", id)));

        menuItemToDelete.getOrders().forEach(order -> order.setMenuItems(null));
        menuRepository.deleteById(id);
    }

}
