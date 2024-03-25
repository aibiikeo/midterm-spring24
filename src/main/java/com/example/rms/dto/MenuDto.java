package com.example.rms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuDto {
    private Long id; 
    @NotNull
    @NotBlank   
    private String name;
    private String description;
    private String price;
    private String category;
    private OrderDto orders;
}
