package com.example.rms.entities;

import java.util.Set;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Tables")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String seatNum;
    @Builder.Default
    private boolean available = true;

    @OneToOne(mappedBy = "table")
    private Customer customer;

    @OneToMany(mappedBy = "table", targetEntity = Order.class, fetch = FetchType.EAGER)
    private Set<Order> orders;
}
