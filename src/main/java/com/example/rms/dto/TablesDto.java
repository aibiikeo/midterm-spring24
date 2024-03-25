package com.example.rms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TablesDto {
    private Long id;
    @NotNull
    @NotBlank
    private int seatNum;
    @Builder.Default
    private boolean available = true;
    private CustomerDto customer;
    private OrderDto orders;
}
