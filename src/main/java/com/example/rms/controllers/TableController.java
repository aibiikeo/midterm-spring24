package com.example.rms.controllers;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    public TablesDto getTableById(@Validated @PathVariable Long id) {
        return tableService.getTableById(id).orElseThrow(() -> new NotFoundException(String.format("Table with id:%d is not found", id)));
    }

    @PostMapping
    public ResponseEntity<TablesDto> postTable(@Validated @RequestBody TablesDto newTable) {
        newTable.setId(null);
        TablesDto saved = tableService.saveTable(newTable);
        return ResponseEntity
                .created(URI.create("/api/v1/customer/" + saved.getId()))
                .body(saved);  
    }

    @PutMapping("{id}")
    public ResponseEntity<TablesDto> putTable(@PathVariable Long id,@Validated @RequestBody TablesDto putTable) {
        putTable.setId(id);
        TablesDto updated = tableService.putTable(putTable);
        return ResponseEntity.ok(updated);
    }

//    @PatchMapping("{id}")
//    public ResponseEntity<TablesDto> patchTable(@PathVariable Long id,@Validated @RequestBody Map<String, Object> patchTable) {
//        TablesDto existing = tableService.getTableById(id)
//                .orElseThrow(() -> new NotFoundException(String.format("Table with id:%d is not found", id)));
//
//        patchTable.forEach((key, value) -> {
//            switch (key) {
//                case "seatNum":
//                    existing.setSeatNum((int) value);
//                    break;
//                case "available":
//                    existing.setAvailable((boolean) value);
//                    break;
//                default:
//                    throw new IllegalArgumentException("Invalid field in patch request: " + key);
//            }
//        });
//
//        TablesDto updatedTables = tableService.patchTable(existing);
//        return ResponseEntity.ok(updatedTables);
//    }

    @PatchMapping("{id}")
    public ResponseEntity<TablesDto> patchTable(@PathVariable Long id, @Validated @RequestBody TablesDto patchTable) {
        TablesDto existing = tableService.getTableById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Table with id:%d is not found", id)));

        existing.setSeatNum(patchTable.getSeatNum());
        existing.setAvailable(patchTable.isAvailable());

        TablesDto updatedTables = tableService.patchTable(existing);
        return ResponseEntity.ok(updatedTables);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@Validated @PathVariable Long id) {
        tableService.deleteTable(id);
        return ResponseEntity.noContent().build();
    }
}
