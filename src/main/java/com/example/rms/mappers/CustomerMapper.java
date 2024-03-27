package com.example.rms.mappers;

import org.mapstruct.Mapper;
import com.example.rms.dto.CustomerDto;
import com.example.rms.dto.OrderDto;
import com.example.rms.dto.TablesDto;
import com.example.rms.entities.Customer;
import com.example.rms.entities.Order;
import com.example.rms.entities.Tables;

@Mapper
public interface CustomerMapper {
    CustomerDto customerToCustomerDto(Customer customer);
    Customer customerDtoToCustomer(CustomerDto customerDto);

    default OrderDto orderToOrderDto(Order order) {
        if (order == null) {
            return null;
        }

        OrderDto.OrderDtoBuilder orderDto = OrderDto.builder();

        orderDto.id(order.getId());
        orderDto.status(order.getStatus());

        return orderDto.build();
    }

    default Order orderDtoToOrder(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.id( orderDto.getId() );
        order.status( orderDto.getStatus() );

        return order.build();
    }    

    default TablesDto tablesToTablesDto(Tables tables) {
        if ( tables == null ) {
            return null;
        }

        TablesDto.TablesDtoBuilder tablesDto = TablesDto.builder();

        tablesDto.available( tables.isAvailable() );
        tablesDto.id( tables.getId() );
        tablesDto.seatNum( tables.getSeatNum() );

        return tablesDto.build();
    }

    default Tables tablesDtoToTables(TablesDto tablesDto) {
        if ( tablesDto == null ) {
            return null;
        }

        Tables.TablesBuilder tables = Tables.builder();

        tables.available( tablesDto.isAvailable() );
        tables.id( tablesDto.getId() );
        tables.seatNum( tablesDto.getSeatNum() );

        return tables.build();
    }
}
