package com.example.rms.entities;

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
    
}
