package com.example.rms.integrationTests;

import com.example.rms.controllers.TableController;
import com.example.rms.dto.TablesDto;
import com.example.rms.repositories.TablesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class TableControllerIT {
    @Autowired
    TableController tableController;

    @Autowired
    TablesRepository tablesRepository;

    @Test
    void getAllTablesTest() {
        List<TablesDto> dto = tableController.getAllTables();

        assertThat(dto.size()).isEqualTo(11);
    }

    @Test
    void getTableByIdTest() {
        Long id = 1L;
        TablesDto tablesDto = tableController.getTableById(id);

        assertThat(tablesDto).isNotNull();
        assertThat(tablesDto.getId()).isEqualTo(id);
    }

    @Test
    void postTableTest() {
        TablesDto newTable = new TablesDto();
        newTable.setSeatNum(6);
        newTable.setAvailable(true);
        TablesDto savedTable = tableController.postTable(newTable).getBody();

        assertThat(savedTable).isNotNull();
        assert savedTable != null;
        assertThat(savedTable.getId()).isNotNull();
        assertThat(savedTable.getSeatNum()).isEqualTo(newTable.getSeatNum());
        assertThat(savedTable.isAvailable()).isEqualTo(newTable.isAvailable());
    }

    @Test
    void putTableTest() {
        Long id = 1L;
        TablesDto putTable = new TablesDto();
        putTable.setId(id);
        putTable.setSeatNum(6);
        putTable.setAvailable(true);
        TablesDto updatedTable = tableController.putTable(id, putTable).getBody();

        assertThat(updatedTable).isNotNull();
        assert updatedTable != null;
        assertThat(updatedTable.getId()).isEqualTo(id);
        assertThat(updatedTable.getSeatNum()).isEqualTo(putTable.getSeatNum());
        assertThat(updatedTable.isAvailable()).isEqualTo(putTable.isAvailable());
    }

    @Test
    void patchTableTest() {
        Long id = 1L;
        TablesDto patchTable = new TablesDto();
        patchTable.setId(id);
        patchTable.setSeatNum(6);
        patchTable.setAvailable(true);
        TablesDto updatedTable = tableController.patchTable(id, patchTable).getBody();

        assertThat(updatedTable).isNotNull();
        assert updatedTable != null;
        assertThat(updatedTable.getId()).isEqualTo(id);
        assertThat(updatedTable.getSeatNum()).isEqualTo(patchTable.getSeatNum());
        assertThat(updatedTable.isAvailable()).isEqualTo(patchTable.isAvailable());
    }
}