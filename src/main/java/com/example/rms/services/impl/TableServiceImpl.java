package com.example.rms.services.impl;

import java.util.List;
import java.util.Optional;

import com.example.rms.controllers.NotFoundException;
import com.example.rms.services.TableService;
import org.springframework.stereotype.Service;
import com.example.rms.dto.TablesDto;
import com.example.rms.entities.Tables;
import com.example.rms.mappers.TablesMapper;
import com.example.rms.repositories.TablesRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {
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

    @Override
    public TablesDto putTable(TablesDto putTable) {
        Tables existing = tablesRepository.findById(putTable.getId())
                .orElseThrow(() -> new EntityNotFoundException("Table not found"));
        existing.setSeatNum(putTable.getSeatNum());
        existing.setAvailable(putTable.isAvailable());
        Tables updated = tablesRepository.save(existing);
        return tablesMapper.tableToTableDto(updated);
    }

    @Override
    public TablesDto patchTable(TablesDto patchTable) {
        Tables existing = tablesRepository.findById(patchTable.getId())
                .orElseThrow(() -> new EntityNotFoundException("Tables not found"));

        if (patchTable.getSeatNum() != -1) {
            existing.setSeatNum(patchTable.getSeatNum());
        }
        if (Boolean.TRUE.equals(patchTable.isAvailable())) {
            existing.setAvailable(true);
        }
        if (Boolean.FALSE.equals(patchTable.isAvailable())) {
            existing.setAvailable(false);
        }

        Tables updated = tablesRepository.save(existing);
        return tablesMapper.tableToTableDto(updated);
    }

    @Override
    public void deleteTable(Long id) {
        tablesRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Table with id:%d is not found", id)));
        tablesRepository.deleteById(id);
    }
}
