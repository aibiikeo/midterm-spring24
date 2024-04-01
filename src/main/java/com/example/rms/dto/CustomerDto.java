package com.example.rms.dto;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String customer;
    private Set<OrderDto> orders;
    private TablesDto table;
}
