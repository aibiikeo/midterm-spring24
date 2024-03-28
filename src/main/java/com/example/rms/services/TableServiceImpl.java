package com.example.rms.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.rms.dto.TablesDto;
import com.example.rms.mappers.TablesMapper;
import com.example.rms.repositories.TablesRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService{
    private final TablesMapper tablesMapper;
    private final TablesRepository tablesRepository;

    @Override
    public List<TablesDto> getAllTables() {
        return tablesRepository.findAll()
                .stream()
                .map(tablesMapper::tableToTableDto)
                .toList();
    }
    
    @Override
    public Optional<TablesDto> getTableById(Long id) {
        return Optional.ofNullable(
            tablesMapper.tableToTableDto(tablesRepository.findById(id).orElse(null))
        );
    }

    @Override
    public TablesDto saveTable(TablesDto newTable) {
        return tablesMapper.tableToTableDto(tablesRepository.save(tablesMapper.tableDtoToTable(newTable)));
    }
}
