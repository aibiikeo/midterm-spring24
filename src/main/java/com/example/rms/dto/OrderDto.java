package com.example.rms.dto;

import java.util.Set;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    @NotNull
    @NotBlank
    private String status;
    private CustomerDto customer;
    private TablesDto table;
    private Set<MenuDto> menuItems;
}
