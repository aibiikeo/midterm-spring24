package com.example.rms.repositoryTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.rms.entities.Table;
import com.example.rms.repositories.TableRepository;

@SpringBootTest
public class TableRepositoryTest {
    
    @Autowired
    private TableRepository tableRepository;

    @Test
    public void testNotNull() {
        List<Table> tables = (List<Table>) tableRepository.findAll();
        assertEquals(11, tables.size());        
    }

    @Test
    void testFindBySeatNum() {
        List<Table> tables = (List<Table>) tableRepository.findBySeatNum(2);
        assertNotNull(tables);
    }
}
