package com.example.rms.controllerTests;

import com.example.rms.controllers.TableController;
import com.example.rms.dto.TablesDto;
import com.example.rms.services.TableService;
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

@WebMvcTest(TableController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class TableControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TableService tableService;

    @Test
    void getAllTables() throws Exception {
        Long tableId = 1L;
        TablesDto tablesDto = TablesDto.builder().id(tableId).seatNum(3).available(true).build();
        Mockito.when(tableService.getAllTables()).thenReturn(Collections.singletonList(tablesDto));

        mockMvc.perform(get("/api/v1/table/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(tableId.toString()))
                .andExpect(jsonPath("$[0].seatNum").value(3))
                .andExpect(jsonPath("$[0].available").value(true));
    }

    @Test
    void getTableById() throws Exception {
        Long tableId = 1L;
        TablesDto tablesDto = TablesDto.builder().id(tableId).seatNum(3).available(true).build();
        Mockito.when(tableService.getTableById(tableId)).thenReturn(Optional.of(tablesDto));

        mockMvc.perform(get("/api/v1/table/{id}", tableId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(tableId.toString()))
                .andExpect(jsonPath("$.seatNum").value(3))
                .andExpect(jsonPath("$.available").value(true));
    }

    @Test
    void postTable() throws Exception {
        Long tableId = 1L;
        TablesDto newTable = TablesDto.builder().seatNum(3).available(true).build();
        TablesDto savedTable = TablesDto.builder().id(tableId).seatNum(3).available(true).build();
        Mockito.when(tableService.saveTable(newTable)).thenReturn(savedTable);

        mockMvc.perform(post("/api/v1/table/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"seatNum\":3, \"available\": true}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(tableId.toString()))
                .andExpect(jsonPath("$.seatNum").value(3))
                .andExpect(jsonPath("$.available").value(true));
    }

    @Test
    void putTable() throws Exception {
        Long tableId = 1L;
        TablesDto putTable = TablesDto.builder().id(tableId).seatNum(3).available(true).build();
        TablesDto updatedTable = TablesDto.builder().id(tableId).seatNum(8).available(false).build();
        Mockito.when(tableService.putTable(putTable)).thenReturn(updatedTable);

        mockMvc.perform(put("/api/v1/table/{id}", tableId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"" + tableId + "\",\"seatNum\":3, \"available\": true}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.id").value(tableId.toString()))
                .andExpect(jsonPath("$.seatNum").value(8))
                .andExpect(jsonPath("$.available").value(false));
    }

    @Test
    void patchTable() throws Exception {
        Long tableId = 1L;
        TablesDto patchTable = TablesDto.builder().id(tableId).seatNum(3).available(true).build();
        TablesDto updatedTable = TablesDto.builder().id(tableId).seatNum(8).available(false).build();
        Mockito.when(tableService.getTableById(tableId)).thenReturn(Optional.of(patchTable));
        Mockito.when(tableService.patchTable(patchTable)).thenReturn(updatedTable);

        mockMvc.perform(patch("/api/v1/table/{id}", tableId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"seatNum\":8, \"available\": false}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(tableId.toString()))
                .andExpect(jsonPath("$.seatNum").value(8))
                .andExpect(jsonPath("$.available").value(false));
    }

    @Test
    void deleteCustomer() throws Exception {
        Long tableId = 1L;

        mockMvc.perform(delete("/api/v1/table/{id}", tableId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
