package com.example.rms.mappers;

import org.mapstruct.Mapper;
import com.example.rms.dto.CustomerDto;
import com.example.rms.dto.MenuDto;
import com.example.rms.dto.OrderDto;
import com.example.rms.dto.TablesDto;
import com.example.rms.entities.Customer;
import com.example.rms.entities.Menu;
import com.example.rms.entities.Order;
import com.example.rms.entities.Tables;

@Mapper
public interface OrderMapper {
    OrderDto orderToOrderDto(Order order);
    Order orderDtoToOrder(OrderDto orderDto);

    default TablesDto tablesToTablesDto(Tables tables) {
        if (tables == null) {
            return null;
        }

        TablesDto.TablesDtoBuilder tablesDto = TablesDto.builder();

        tablesDto.available(tables.isAvailable());
        tablesDto.id(tables.getId());
        tablesDto.seatNum(tables.getSeatNum());

        return tablesDto.build();
    }

    default Tables tablesDtoToTables(TablesDto tablesDto) {
        if (tablesDto == null) {
            return null;
        }

        Tables.TablesBuilder tables = Tables.builder();

        tables.available(tablesDto.isAvailable());
        tables.id(tablesDto.getId());
        tables.seatNum(tablesDto.getSeatNum());

        return tables.build();
    }

    default CustomerDto customerToCustomerDto(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerDto.CustomerDtoBuilder customerDto = CustomerDto.builder();

        customerDto.customer(customer.getUsername());
        customerDto.id(customer.getId());

        return customerDto.build();
    }

    default Customer customerDtoToCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.username(customerDto.getCustomer());
        customer.id(customerDto.getId());

        return customer.build();
    }

    default MenuDto menuToMenuDto(Menu menu) {
        if (menu == null) {
            return null;
        }

        MenuDto.MenuDtoBuilder menuDto = MenuDto.builder();

        menuDto.category(menu.getCategory());
        menuDto.description(menu.getDescription());
        menuDto.id(menu.getId());
        menuDto.name(menu.getName());
        menuDto.price(menu.getPrice());

        return menuDto.build();
    }

    default Menu menuDtoToMenu(MenuDto menuDto) {
        if (menuDto == null) {
            return null;
        }

        Menu.MenuBuilder menu = Menu.builder();

        menu.category(menuDto.getCategory());
        menu.description(menuDto.getDescription());
        menu.id(menuDto.getId());
        menu.name(menuDto.getName());
        menu.price(menuDto.getPrice());

        return menu.build();
    }
}
