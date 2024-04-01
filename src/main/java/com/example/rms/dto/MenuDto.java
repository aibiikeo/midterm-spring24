package com.example.rms.dto;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuDto {
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String name;
    private String description;
    @NotBlank
    @Column(nullable = false)
    private String price;
    private String category;
    private Set<OrderDto> orders;
}
