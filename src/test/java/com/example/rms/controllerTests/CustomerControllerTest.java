package com.example.rms.controllerTests;

import com.example.rms.controllers.CustomerController;
import com.example.rms.dto.CustomerDto;
import com.example.rms.services.CustomerService;
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

@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    @Test
    void getAllCustomers() throws Exception {
        Long customerId = 1L;
        CustomerDto customerDto = CustomerDto.builder().id(customerId).customer("Customer1").build();
        Mockito.when(customerService.getAllCustomers()).thenReturn(Collections.singletonList(customerDto));

        mockMvc.perform(get("/api/v1/customer/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(customerId.toString()))
                .andExpect(jsonPath("$[0].customer").value("Customer1"));
    }

    @Test
    void getCustomerById() throws Exception {
        Long customerId = 1L;
        CustomerDto customerDto = CustomerDto.builder().id(customerId).customer("Customer1").build();
        Mockito.when(customerService.getCustomerById(customerId)).thenReturn(Optional.of(customerDto));

        mockMvc.perform(get("/api/v1/customer/{id}", customerId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(customerId))
                .andExpect(jsonPath("$.customer").value("Customer1"));
    }

    @Test
    void postCustomer() throws Exception {
        Long customerId = 1L;
        CustomerDto newCustomer = CustomerDto.builder().customer("Customer1").build();
        CustomerDto savedCustomer = CustomerDto.builder().id(customerId).customer("Customer1").build();
        Mockito.when(customerService.saveCustomer(newCustomer)).thenReturn(savedCustomer);

        mockMvc.perform(post("/api/v1/customer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customer\":\"Customer1\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(customerId.toString()))
                .andExpect(jsonPath("$.customer").value("Customer1"));
    }

    @Test
    void putCustomer() throws Exception {
        Long customerId = 1L;
        CustomerDto putCustomer = CustomerDto.builder().id(customerId).customer("Customer1").build();
        CustomerDto updatedCustomer = CustomerDto.builder().id(customerId).customer("Alisa").build();
        Mockito.when(customerService.putCustomer(putCustomer)).thenReturn(updatedCustomer);

        mockMvc.perform(put("/api/v1/customer/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"" + customerId + "\",\"customer\":\"Customer1\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.id").value(customerId))
                .andExpect(jsonPath("$.customer").value("Alisa"));
    }

    @Test
    void patchCustomer() throws Exception {
        Long customerId = 1L;
        CustomerDto patchCustomer = CustomerDto.builder().id(customerId).customer("Customer1").build();
        CustomerDto updatedCustomer = CustomerDto.builder().id(customerId).customer("Alisa").build();
        Mockito.when(customerService.getCustomerById(customerId)).thenReturn(Optional.of(patchCustomer));
        Mockito.when(customerService.patchCustomer(patchCustomer)).thenReturn(updatedCustomer);

        mockMvc.perform(patch("/api/v1/customer/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customer\":\"Alisa\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(customerId.toString()))
                .andExpect(jsonPath("$.customer").value("Alisa"));
    }

    @Test
    void deleteCustomer() throws Exception {
        Long customerId = 1L;

        mockMvc.perform(delete("/api/v1/customer/{id}", customerId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
