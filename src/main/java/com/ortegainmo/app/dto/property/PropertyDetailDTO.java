package com.ortegainmo.app.dto.property;

import com.ortegainmo.app.dto.characteristics.CharacteristicsDTO;
import com.ortegainmo.app.dto.image.ImageDTO;
import com.ortegainmo.app.dto.location.LocationDTO;
import com.ortegainmo.app.enums.OperationType;
import com.ortegainmo.app.enums.PropertyStatus;
import com.ortegainmo.app.enums.PropertyType;

import java.util.List;

public record PropertyDetailDTO(
        Long id,
        String title,
        String description,
        Double price,
        PropertyStatus status,
        PropertyType propertyType,
        OperationType operationType,
        LocationDTO location,
        CharacteristicsDTO characteristics,
        List<ImageDTO> images
) {}
