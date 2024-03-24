package com.example.rms.entities;

import java.util.Set;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    
    private String name;
    private String description;
    private String price;
    private String category;
    
    @ManyToMany(mappedBy = "menuItems")
    private Set<Order> orders;
}
