package com.example.rms.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
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
}
