package com.ortegainmo.demo.dto.location;

import jakarta.validation.constraints.NotBlank;

public record LocationDTO(
        @NotBlank(message = "La calle es obligatoria") String street,
        @NotBlank(message = "La altura es obligatoria") String streetNumber,
        String floor,
        String apartment
) {}
