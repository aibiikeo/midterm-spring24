package com.example.rms.entities;

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
    private Table table;

    @ManyToMany
    private Set<Menu> menuItems;
}
