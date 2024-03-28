package com.example.rms.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.rms.dto.MenuDto;
import com.example.rms.mappers.MenuMapper;
import com.example.rms.repositories.MenuRepository;
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
}
