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
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String name;
    private String description;
    @NotBlank
    @Column(nullable = false)
    private String price;
    private String category;
    
    @ManyToMany(mappedBy = "menuItems")
    private Set<Order> orders;

    @Override
public int hashCode() {
    return Objects.hash(id, name, description, price, category);
}

@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
        return false;
    }
    Menu menu = (Menu) obj;
    return Objects.equals(id, menu.id) && Objects.equals(name, menu.name)
            && Objects.equals(description, menu.description) && Objects.equals(price, menu.price)
            && Objects.equals(category, menu.category);
}

}
