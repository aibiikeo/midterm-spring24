package com.example.rms.entities;

import java.util.Objects;
import java.util.Set;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String status;
    // new, preparing, ready, delivered, cancelled

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Tables table;

    @ManyToMany
    private Set<Menu> menuItems;

    @Override
public int hashCode() {
    return Objects.hash(id, status);
}

@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
        return false;
    }
    Order order = (Order) obj;
    return Objects.equals(id, order.id) && Objects.equals(status, order.status);
}

}
