package com.example.rms.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.rms.dto.TablesDto;
import com.example.rms.entities.Tables;

@Mapper
public interface TablesMapper {
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "orders", ignore = true)
    TablesDto tableToTableDto(Tables table);

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Tables tableDtoToTable(TablesDto tableDto);
}
