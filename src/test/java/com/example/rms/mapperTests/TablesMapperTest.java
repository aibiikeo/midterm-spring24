package com.example.rms.mapperTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.rms.dto.TablesDto;
import com.example.rms.entities.Tables;
import com.example.rms.mappers.TablesMapper;

@SpringBootTest
class TablesMapperTest {
    @Autowired
    TablesMapper tablesMapper;

    @Test
    void tableToTableDto(){
        Tables table = new Tables();
        table.setId(1L);
        table.setSeatNum(4);
        TablesDto tableDto = tablesMapper.tableToTableDto(table);
        assertNotNull(tableDto);
        assertEquals(table.getId(), tableDto.getId());
        assertEquals(table.getSeatNum(), tableDto.getSeatNum());
    }

    @Test
    void tableDtoToTable(){
        TablesDto tableDto = new TablesDto();
        tableDto.setId(1L);
        tableDto.setSeatNum(4);
        Tables table = tablesMapper.tableDtoToTable(tableDto);
        assertNotNull(table);
        assertEquals(tableDto.getId(), table.getId());
        assertEquals(tableDto.getSeatNum(), table.getSeatNum());
    }    
}
