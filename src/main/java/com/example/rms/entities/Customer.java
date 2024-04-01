package com.example.rms.entities;

import java.util.Objects;
import java.util.Set;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    @Column(nullable = false)
    private String customer;

    @OneToMany(mappedBy = "customer", targetEntity = Order.class, cascade = CascadeType.REMOVE)
    private Set<Order> orders;

    @OneToOne(cascade = CascadeType.REMOVE)
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
