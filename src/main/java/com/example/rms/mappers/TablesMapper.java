package com.example.rms.mappers;

import org.mapstruct.Mapper;
import com.example.rms.dto.CustomerDto;
import com.example.rms.dto.OrderDto;
import com.example.rms.dto.TablesDto;
import com.example.rms.entities.Customer;
import com.example.rms.entities.Order;
import com.example.rms.entities.Tables;

@Mapper
public interface TablesMapper {
    TablesDto tableToTableDto(Tables table);
    Tables tableDtoToTable(TablesDto tableDto);

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

    default CustomerDto customerToCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto.CustomerDtoBuilder customerDto = CustomerDto.builder();

        customerDto.customer( customer.getCustomer() );
        customerDto.id( customer.getId() );

        return customerDto.build();
    }

    default Customer customerDtoToCustomer(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.customer( customerDto.getCustomer() );
        customer.id( customerDto.getId() );

        return customer.build();
    }
}
