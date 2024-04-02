package com.example.rms.integrationTests;

import com.example.rms.controllers.MenuController;
import com.example.rms.dto.MenuDto;
import com.example.rms.repositories.MenuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class MenuControllerIT {
    @Autowired
    MenuController menuController;

    @Autowired
    MenuRepository menuRepository;

    @Test
    void getAllMenuItemsTest() {
        List<MenuDto> dto = menuController.getAllMenuItems();

        assertThat(dto.size()).isEqualTo(27);
    }

    @Test
    void getMenuItemByIdTest() {
        Long id = 1L;
        MenuDto menuDto = menuController.getMenuItemById(id);

        assertThat(menuDto).isNotNull();
        assertThat(menuDto.getId()).isEqualTo(id);
    }

    @Test
    void postMenuItemTest() {
        MenuDto putMenuItem = new MenuDto();
        putMenuItem.setName("Bread");
        putMenuItem.setDescription("bread");
        putMenuItem.setCategory("Main Course");
        putMenuItem.setPrice("1$");
        MenuDto updatedMenuItem = menuController.postMenuItem(putMenuItem).getBody();

        assertThat(updatedMenuItem).isNotNull();
        assert updatedMenuItem != null;
        assertThat(updatedMenuItem.getId()).isNotNull();
        assertThat(updatedMenuItem.getName()).isEqualTo(putMenuItem.getName());
        assertThat(updatedMenuItem.getDescription()).isEqualTo(putMenuItem.getDescription());
        assertThat(updatedMenuItem.getCategory()).isEqualTo(putMenuItem.getCategory());
        assertThat(updatedMenuItem.getPrice()).isEqualTo(putMenuItem.getPrice());
    }

    @Test
    void putMenuItemTest() {
        Long id = 1L;
        MenuDto putMenuItem = new MenuDto();
        putMenuItem.setId(id);
        putMenuItem.setName("Bread");
        putMenuItem.setDescription("bread");
        putMenuItem.setCategory("Main Course");
        putMenuItem.setPrice("1$");
        MenuDto updatedMenuItem = menuController.putMenuItem(id, putMenuItem).getBody();

        assertThat(updatedMenuItem).isNotNull();
        assert updatedMenuItem != null;
        assertThat(updatedMenuItem.getId()).isEqualTo(id);
        assertThat(updatedMenuItem.getId()).isNotNull();
        assertThat(updatedMenuItem.getName()).isEqualTo(putMenuItem.getName());
        assertThat(updatedMenuItem.getDescription()).isEqualTo(putMenuItem.getDescription());
        assertThat(updatedMenuItem.getCategory()).isEqualTo(putMenuItem.getCategory());
        assertThat(updatedMenuItem.getPrice()).isEqualTo(putMenuItem.getPrice());
    }

    @Test
    void patchMenuItemTest() {
        Long id = 1L;
        MenuDto patchMenuItem = new MenuDto();
        patchMenuItem.setId(id);
        patchMenuItem.setDescription("white bread");
        MenuDto patchedMenuItem = menuController.patchMenuItem(id, patchMenuItem).getBody();

        assertThat(patchedMenuItem).isNotNull();
        assert patchedMenuItem != null;
        assertThat(patchedMenuItem.getId()).isEqualTo(id);
        assertThat(patchedMenuItem.getDescription()).isEqualTo(patchMenuItem.getDescription());
    }

}
