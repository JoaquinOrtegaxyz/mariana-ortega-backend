package com.ortegainmo.app.dto.property;

import com.ortegainmo.app.enums.OperationType;
import com.ortegainmo.app.enums.PropertyType;

public record PropertyListDTO(
        Long id,
        String title,
        Double price,
        PropertyType propertyType,
        OperationType operationType,
        String coverImageUrl,
        Integer bedrooms,
        Integer bathrooms,
        String street,
        String streetNumber
) {}
