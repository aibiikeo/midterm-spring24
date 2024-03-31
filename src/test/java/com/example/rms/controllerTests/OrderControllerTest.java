package com.example.rms.controllerTests;

import com.example.rms.controllers.OrderController;
import com.example.rms.dto.OrderDto;
import com.example.rms.services.OrderService;
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

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    OrderService orderService;

    @Test
    void getAllOrders() throws Exception{
        Long orderId = 1L;
        OrderDto orderDto = OrderDto.builder().id(orderId).status("new").build();
        Mockito.when(orderService.getAllOrders()).thenReturn(Collections.singletonList(orderDto));

        mockMvc.perform(get("/api/v1/order/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(orderId.toString()))
                .andExpect(jsonPath("$[0].status").value("new"));
    }

    @Test
    void getOrderById() throws Exception{
        Long orderId = 1L;
        OrderDto orderDto = OrderDto.builder().id(orderId).status("new").build();
        Mockito.when(orderService.getOrderById(orderId)).thenReturn(Optional.of(orderDto));

        mockMvc.perform(get("/api/v1/order/{id}", orderId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(orderId))
                .andExpect(jsonPath("$.status").value("new"));
    }

    @Test
    void postOrder() throws Exception{
        Long orderId = 1L;
        OrderDto newOrder = OrderDto.builder().status("new").build();
        OrderDto savedOrder = OrderDto.builder().id(orderId).status("new").build();
        Mockito.when(orderService.saveOrder(newOrder)).thenReturn(savedOrder);

        mockMvc.perform(post("/api/v1/order/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\":\"new\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(orderId.toString()))
                .andExpect(jsonPath("$.status").value("new"));
    }

    @Test
    void putOrder() throws Exception{
        Long orderId = 1L;
        OrderDto putOrder = OrderDto.builder().id(orderId).status("new").build();
        OrderDto updatedOrder = OrderDto.builder().id(orderId).status("ready").build();
        Mockito.when(orderService.putOrder(putOrder)).thenReturn(updatedOrder);

        mockMvc.perform(put("/api/v1/order/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"" + orderId + "\",\"status\":\"new\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.id").value(orderId))
                .andExpect(jsonPath("$.status").value("ready"));
    }

    @Test
    void patchOrder() throws Exception{
        Long orderId = 1L;
        OrderDto patchOrder = OrderDto.builder().id(orderId).status("new").build();
        OrderDto updatedOrder = OrderDto.builder().id(orderId).status("ready").build();
        Mockito.when(orderService.getOrderById(orderId)).thenReturn(Optional.of(patchOrder));
        Mockito.when(orderService.patchOrder(patchOrder)).thenReturn(updatedOrder);

        mockMvc.perform(patch("/api/v1/order/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\":\"ready\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(orderId.toString()))
                .andExpect(jsonPath("$.status").value("ready"));
    }

    @Test
    void deleteOrder() throws Exception{
        Long orderId = 1L;

        mockMvc.perform(delete("/api/v1/order/{id}", orderId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
