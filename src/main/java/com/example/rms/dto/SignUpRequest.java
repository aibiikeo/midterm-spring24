package com.example.rms.dto;

import com.example.rms.entities.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpRequest {
    @NotBlank
    @Column(nullable = false)
    private String username;
    private String password;
    private Role role;
}
