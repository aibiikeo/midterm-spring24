package com.example.rms.mappers;

import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.rms.dto.OrderDto;
import com.example.rms.dto.TablesDto;
import com.example.rms.entities.Order;
import com.example.rms.entities.Tables;

@Mapper
public interface TablesMapper {
    TablesDto tableToTableDto(Tables table);
    Tables tableDtoToTable(TablesDto tableDto);

    @Mapping(target = "orders", source = "value")
    Set<OrderDto> map(Set<Order> value);
}
