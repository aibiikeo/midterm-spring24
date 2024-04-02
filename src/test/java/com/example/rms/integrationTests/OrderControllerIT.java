package com.example.rms.integrationTests;

import com.example.rms.controllers.OrderController;
import com.example.rms.dto.OrderDto;
import com.example.rms.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class OrderControllerIT {
    @Autowired
    OrderController orderController;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void getAllOrdersTest() {
        List<OrderDto> dto = orderController.getAllOrders();

        assertThat(dto.size()).isEqualTo(7);
    }

    @Test
    void getOrderByIdTest() {
        Long id = 1L;
        OrderDto orderDto = orderController.getOrderById(id);

        assertThat(orderDto).isNotNull();
        assertThat(orderDto.getId()).isEqualTo(id);
    }

    @Test
    void postOrderTest() {
        OrderDto newOrder = new OrderDto();
        newOrder.setStatus("preparing");
        OrderDto savedOrder = orderController.postOrder(newOrder).getBody();

        assertThat(savedOrder).isNotNull();
        assert savedOrder != null;
        assertThat(savedOrder.getId()).isNotNull();
        assertThat(savedOrder.getStatus()).isEqualTo(newOrder.getStatus());
    }

    @Test
    void putOrderTest() {
        Long id = 1L;
        OrderDto putOrder = new OrderDto();
        putOrder.setId(id);
        putOrder.setStatus("preparing");
        OrderDto updatedOrder = orderController.putOrder(id, putOrder).getBody();

        assertThat(updatedOrder).isNotNull();
        assert updatedOrder != null;
        assertThat(updatedOrder.getId()).isEqualTo(id);
        assertThat(updatedOrder.getStatus()).isEqualTo(putOrder.getStatus());

    }

    @Test
    void patchOrderTest() {
        Long id = 1L;
        OrderDto patchCustomer = new OrderDto();
        patchCustomer.setId(id);
        patchCustomer.setStatus("preparing");
        OrderDto updatedOrder = orderController.patchOrder(id, patchCustomer).getBody();

        assertThat(updatedOrder).isNotNull();
        assert updatedOrder != null;
        assertThat(updatedOrder.getId()).isEqualTo(id);
        assertThat(updatedOrder.getStatus()).isEqualTo(patchCustomer.getStatus());

    }

}