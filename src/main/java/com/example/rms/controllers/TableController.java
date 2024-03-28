package com.example.rms.controllers;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.rms.dto.TablesDto;
import com.example.rms.services.TableService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/table/")
public class TableController {
    private final TableService tableService;
    
    @GetMapping  
    public List<TablesDto> getAllTables(){
        return tableService.getAllTables();
    }
    
    @GetMapping("{id}")
    public TablesDto getTableById(@PathVariable Long id) {
        return tableService.getTableById(id).orElseThrow();
    }

    @PostMapping
    public ResponseEntity<TablesDto> newTable(@RequestBody TablesDto newTable) {
        newTable.setId(null);
        TablesDto saved = tableService.saveTable(newTable);
        return ResponseEntity
                .created(URI.create("/api/v1/customer/" + saved.getId()))
                .body(saved);  
    }

    @PutMapping("{id}")
    public ResponseEntity<TablesDto> putTable(@PathVariable Long id, @RequestBody TablesDto putTable) {
        putTable.setId(id);
        TablesDto updated = tableService.putTable(putTable);
        return ResponseEntity.ok(updated);
    }
}
