package com.example.rms.services;

import java.util.List;
import java.util.Optional;
import com.example.rms.dto.TablesDto;

public interface TableService {
    List<TablesDto> getAllTables();
    Optional<TablesDto> getTableById(Long id);
    TablesDto saveTable(TablesDto newTable);
    TablesDto putTable(TablesDto putTable);
    TablesDto patchTable(TablesDto patchTable);
    void deleteTable(Long id);
}
