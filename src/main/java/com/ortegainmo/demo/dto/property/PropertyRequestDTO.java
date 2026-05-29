package com.ortegainmo.demo.dto.property;

import com.ortegainmo.demo.dto.characteristics.CharacteristicsDTO;
import com.ortegainmo.demo.dto.location.LocationDTO;
import com.ortegainmo.demo.enums.OperationType;
import com.ortegainmo.demo.enums.PropertyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PropertyRequestDTO(
        @NotBlank(message = "El título no puede estar vacío") String title,
        @NotBlank(message = "La descripción no puede estar vacía") String description,
        @NotNull @Positive(message = "El precio debe ser mayor a 0") Double price,
        @NotNull PropertyType propertyType,
        @NotNull OperationType operationType,
        @NotNull LocationDTO location,
        @NotNull CharacteristicsDTO characteristics
) {}
