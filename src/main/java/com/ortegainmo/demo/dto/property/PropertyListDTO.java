package com.ortegainmo.demo.dto.property;

import com.ortegainmo.demo.enums.OperationType;
import com.ortegainmo.demo.enums.PropertyType;

public record PropertyListDTO(
        Long id,
        String title,
        Double price,
        PropertyType propertyType,
        OperationType operationType,
        String coverImageUrl, // Solo mandamos la foto de portada para que Angular cargue rápido
        Integer bedrooms,
        Integer bathrooms,
        String street // Para mostrar la zona rápida ("Calle Falsa 123")
) {}
