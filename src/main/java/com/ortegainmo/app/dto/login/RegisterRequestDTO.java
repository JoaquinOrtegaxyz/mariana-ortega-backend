package com.ortegainmo.app.dto.login;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDTO(
        @NotBlank(message = "El email no puede estar vacío") String email,
        @NotBlank(message = "La contraseña no puede estar vacía") String password
) {}
