package com.example.rms.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Customer extends User {

    @OneToMany(mappedBy = "customer", targetEntity = Order.class, cascade = CascadeType.REMOVE)
    private Set<Order> orders;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Tables table;

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUsername());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Customer customer = (Customer) obj;
        return Objects.equals(getId(), customer.getId()) && Objects.equals(getUsername(), customer.getUsername());
    }

}
