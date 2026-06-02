package com.ortegainmo.app.dto.characteristics;

import jakarta.validation.constraints.PositiveOrZero;

public record CharacteristicsDTO(
        @PositiveOrZero Integer bedrooms,
        @PositiveOrZero Integer bathrooms,
        @PositiveOrZero Double totalArea,
        @PositiveOrZero Double coveredArea,
        Boolean hasGarage,
        @PositiveOrZero Integer age,
        Double latitude,
        Double longitude
) {}
