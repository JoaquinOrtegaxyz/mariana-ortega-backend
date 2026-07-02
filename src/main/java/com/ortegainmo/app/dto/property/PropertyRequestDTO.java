package com.ortegainmo.app.dto.property;

import com.ortegainmo.app.dto.characteristics.CharacteristicsDTO;
import com.ortegainmo.app.dto.location.LocationDTO;
import com.ortegainmo.app.enums.OperationType;
import com.ortegainmo.app.enums.PropertyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record PropertyRequestDTO(
        @NotBlank(message = "El título no puede estar vacío") String title,
        String description,
        @NotNull @PositiveOrZero(message = "El precio no puede ser negativo") Double price,
        @NotNull PropertyType propertyType,
        @NotNull OperationType operationType,
        @NotNull LocationDTO location,
        @NotNull CharacteristicsDTO characteristics
) {}
