package com.ortegainmo.demo.dto.login;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank(message = "El email no puede estar vacío") String email,
        @NotBlank(message = "La contraseña no puede estar vacía") String password
) {}
