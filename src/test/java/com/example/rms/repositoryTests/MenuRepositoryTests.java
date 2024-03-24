package com.example.rms.repositoryTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.rms.entities.Menu;
import com.example.rms.repositories.MenuRepository;

@SpringBootTest
public class MenuRepositoryTests {
    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void testNotNull() {
        List<Menu> menu = (List<Menu>) menuRepository.findAll();
        assertEquals(27, menu.size());        
    }

    @Test
    public void testFindByName() {
        List<Menu> menu = menuRepository.findByName("Cake");
        assertEquals(1, menu.size());
        assertEquals("Dessert", menu.get(0).getCategory());        
    }

    @Test
    public void testFindByCategory() {
        List<Menu> menu = menuRepository.findByCategory("Drink");
        assertEquals("water", menu.get(0).getDescription());      
    }

    @Test
    public void testFindByPrice() {
        List<Menu> menu = menuRepository.findByPrice("0.5$");
        assertEquals("0.5$", menu.get(0).getPrice());   
        
    }
}
