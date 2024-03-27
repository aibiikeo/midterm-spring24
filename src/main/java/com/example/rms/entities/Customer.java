package com.example.rms.entities;

import java.util.Objects;
import java.util.Set;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String customer;

    @OneToMany(mappedBy = "customer", targetEntity = Order.class)
    private Set<Order> orders;

    @OneToOne
    private Tables table;
    
    @Override
    public int hashCode() {
        return Objects.hash(id, customer);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Customer customer = (Customer) obj;
        return Objects.equals(id, customer.id) && Objects.equals(this.customer, customer.customer);
    }
}
