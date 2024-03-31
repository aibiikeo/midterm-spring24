package com.example.rms.controllerTests;

import com.example.rms.controllers.MenuController;
import com.example.rms.dto.MenuDto;
import com.example.rms.services.MenuService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import java.util.Collections;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(MenuController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class MenuControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    MenuService menuService;

    @Test
    void getAllMenuItems() throws Exception{
        Long menuItemId = 1L;
        MenuDto menuDto = MenuDto.builder().id(menuItemId).name("Rice").description("white rice").category("Main cource").price("1$").build();
        Mockito.when(menuService.getAllMenuItems()).thenReturn(Collections.singletonList(menuDto));

        mockMvc.perform(get("/api/v1/menu/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(menuItemId.toString()))
                .andExpect(jsonPath("$[0].name").value("Rice"))
                .andExpect(jsonPath("$[0].description").value("white rice"))
                .andExpect(jsonPath("$[0].category").value("Main cource"))
                .andExpect(jsonPath("$[0].price").value("1$"));
    }

    @Test
    void getMenuItemById() throws Exception{
        Long menuItemId = 1L;
        MenuDto menuDto = MenuDto.builder().id(menuItemId).name("Rice").description("white rice").category("Main cource").price("1$").build();
        Mockito.when(menuService.getMenuItemById(menuItemId)).thenReturn(Optional.of(menuDto));

        mockMvc.perform(get("/api/v1/menu/{id}", menuItemId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(menuItemId.toString()))
                .andExpect(jsonPath("$.name").value("Rice"))
                .andExpect(jsonPath("$.description").value("white rice"))
                .andExpect(jsonPath("$.category").value("Main cource"))
                .andExpect(jsonPath("$.price").value("1$"));
    }

    @Test
    void postMenuItem() throws Exception{
        Long menuItemId = 1L;
        MenuDto newMenuItem = MenuDto.builder().name("Rice").description("white rice").category("Main cource").price("1$").build();
        MenuDto savedMenuItem = MenuDto.builder().id(menuItemId).name("Rice").description("white rice").category("Main cource").price("1$").build();
        Mockito.when(menuService.saveMenuItem(newMenuItem)).thenReturn(savedMenuItem);

        mockMvc.perform(post("/api/v1/menu/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Rice\", \"description\":\"white rice\", \"category\":\"Main cource\", \"price\":\"1$\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(menuItemId.toString()))
                .andExpect(jsonPath("$.name").value("Rice"))
                .andExpect(jsonPath("$.description").value("white rice"))
                .andExpect(jsonPath("$.category").value("Main cource"))
                .andExpect(jsonPath("$.price").value("1$"));
    }

    @Test
    void putMenuItem() throws Exception{
        Long menuItemId = 1L;
        MenuDto putMenuItem = MenuDto.builder().id(menuItemId).name("Rice").description("white rice").category("Main cource").price("1$").build();
        MenuDto updatedMenuItem = MenuDto.builder().id(menuItemId).name("Sandwich").description("sandwich").category("Main cource").price("5$").build();
        Mockito.when(menuService.putMenuItem(putMenuItem)).thenReturn(updatedMenuItem);

        mockMvc.perform(put("/api/v1/menu/{id}", menuItemId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"" + menuItemId + "\", \"name\":\"Rice\", \"description\":\"white rice\", \"category\":\"Main cource\", \"price\":\"1$\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.id").value(menuItemId.toString()))
                .andExpect(jsonPath("$.name").value("Sandwich"))
                .andExpect(jsonPath("$.description").value("sandwich"))
                .andExpect(jsonPath("$.category").value("Main cource"))
                .andExpect(jsonPath("$.price").value("5$"));
    }

    @Test
    void patchMenuItem() throws Exception{
        Long menuItemId = 1L;
        MenuDto patchMenuItem = MenuDto.builder().id(menuItemId).name("Rice").description("white rice").category("Main cource").price("1$").build();
        MenuDto updatedMenuItem = MenuDto.builder().id(menuItemId).name("Sandwich").description("sandwich").category("Main cource").price("5$").build();
        Mockito.when(menuService.getMenuItemById(menuItemId)).thenReturn(Optional.of(patchMenuItem));
        Mockito.when(menuService.patchMenuItem(patchMenuItem)).thenReturn(updatedMenuItem);

        mockMvc.perform(patch("/api/v1/menu/{id}", menuItemId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Rice\", \"description\":\"white rice\", \"category\":\"Main cource\", \"price\":\"1$\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(menuItemId.toString()))
                .andExpect(jsonPath("$.name").value("Sandwich"))
                .andExpect(jsonPath("$.description").value("sandwich"))
                .andExpect(jsonPath("$.category").value("Main cource"))
                .andExpect(jsonPath("$.price").value("5$"));
    }

    @Test
    void deleteMenuItem() throws Exception{
        Long menuItemId = 1L;

        mockMvc.perform(delete("/api/v1/menu/{id}", menuItemId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
