package com.example.rms.services;

import java.util.List;
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
    
}
