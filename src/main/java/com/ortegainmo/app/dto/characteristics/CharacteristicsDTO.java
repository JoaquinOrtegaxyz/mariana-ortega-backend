package com.ortegainmo.app.dto.characteristics;

import jakarta.validation.constraints.PositiveOrZero;

public record CharacteristicsDTO(
        @PositiveOrZero Integer bedrooms,
        @PositiveOrZero Integer bathrooms,
        @PositiveOrZero Double lotArea,
        @PositiveOrZero Double totalArea,
        Boolean hasGarage,
        @PositiveOrZero Integer age,
        Double latitude,
        Double longitude
) {}
