package com.example.rms.repositoryTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.rms.entities.Customer;
import com.example.rms.repositories.CustomerRepository;

@SpringBootTest
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testNotNull() {
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        assertEquals(9, customers.size());        
    }

    @Test
    void testFindByCustomer() {
        List<Customer> customers = customerRepository.findByCustomer("Lena");
        assertNotNull(customers);
        assertEquals("Lena", customers.get(0).getCustomer());
        assertEquals(9, customers.get(0).getId());
    }
}
