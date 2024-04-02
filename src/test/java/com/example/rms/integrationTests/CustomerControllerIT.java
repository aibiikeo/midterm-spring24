package com.example.rms.integrationTests;

import com.example.rms.controllers.CustomerController;
import com.example.rms.dto.CustomerDto;
import com.example.rms.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class CustomerControllerIT {
    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void getAllCustomersTest() {
        List<CustomerDto> dto = customerController.getAllCustomers();

        assertThat(dto.size()).isEqualTo(9);
    }

    @Test
    void getCustomerByIdTest() {
        Long id = 1L;
        CustomerDto customerDto = customerController.getCustomerById(id);

        assertThat(customerDto).isNotNull();
        assertThat(customerDto.getId()).isEqualTo(id);
    }

    @Test
    void postCustomerTest() {
        CustomerDto newCustomer = new CustomerDto();
        newCustomer.setCustomer("Lena");
        CustomerDto savedCustomer = customerController.postCustomer(newCustomer).getBody();

        assertThat(savedCustomer).isNotNull();
        assert savedCustomer != null;
        assertThat(savedCustomer.getId()).isNotNull();
        assertThat(savedCustomer.getCustomer()).isEqualTo(newCustomer.getCustomer());
    }

    @Test
    void putCustomerTest() {
        Long id = 1L;
        CustomerDto putCustomer = new CustomerDto();
        putCustomer.setId(id);
        putCustomer.setCustomer("Alisa");
        CustomerDto updatedCustomer = customerController.putCustomer(id, putCustomer).getBody();

        assertThat(updatedCustomer).isNotNull();
        assert updatedCustomer != null;
        assertThat(updatedCustomer.getId()).isEqualTo(id);
        assertThat(updatedCustomer.getCustomer()).isEqualTo(putCustomer.getCustomer());
    }

    @Test
    void patchCustomerTest() {
        Long id = 1L;
        CustomerDto patchCustomer = new CustomerDto();
        patchCustomer.setId(id);
        patchCustomer.setCustomer("Alisa");
        CustomerDto patchedCustomer = customerController.patchCustomer(id, patchCustomer).getBody();

        assertThat(patchedCustomer).isNotNull();
        assert patchedCustomer != null;
        assertThat(patchedCustomer.getId()).isEqualTo(id);
        assertThat(patchedCustomer.getCustomer()).isEqualTo(patchCustomer.getCustomer());
    }
}
