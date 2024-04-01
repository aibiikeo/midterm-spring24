package com.example.rms.dto;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String status;
    private CustomerDto customer;
    private TablesDto table;
    private Set<MenuDto> menuItems;
}
