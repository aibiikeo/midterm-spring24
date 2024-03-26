package com.example.rms.dto;

import java.util.Set;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private Long id;
    @NotNull
    @NotBlank
    private String customer;
    private Set<OrderDto> orders;
    private TablesDto table;
}
