package com.ortegainmo.app.dto.location;

import com.ortegainmo.app.enums.Zone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LocationDTO(
        @NotBlank(message = "La calle es obligatoria") String street,
        @NotBlank(message = "La altura es obligatoria") String streetNumber,
        @NotNull(message = "La zona es obligatoria") Zone zone,
        String floor,
        String apartment
) {}
