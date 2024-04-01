package com.example.rms.entities;

import java.util.Objects;
import java.util.Set;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Column(nullable = false)
    private int seatNum;
    @Builder.Default
    private boolean available = true;

    @OneToOne(mappedBy = "table", cascade = CascadeType.REMOVE)
    private Customer customer;

    @OneToMany(mappedBy = "table", targetEntity = Order.class, cascade = CascadeType.REMOVE)
    private Set<Order> orders;

    @Override
    public int hashCode() {
        return Objects.hash(id, seatNum, available);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Tables tables = (Tables) obj;
        return seatNum == tables.seatNum && available == tables.available && Objects.equals(id, tables.id);
    }
}
