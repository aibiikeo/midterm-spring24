package com.example.rms.entities;

import java.util.Set;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int seatNum;
    @Builder.Default
    private boolean available = true;

    @OneToOne(mappedBy = "table")
    private Customer customer;

    @OneToMany(mappedBy = "table", targetEntity = Order.class)
    private Set<Order> orders;
}
