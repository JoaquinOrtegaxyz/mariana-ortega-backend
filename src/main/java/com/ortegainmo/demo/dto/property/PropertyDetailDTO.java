package com.ortegainmo.demo.dto.property;

import com.ortegainmo.demo.dto.characteristics.CharacteristicsDTO;
import com.ortegainmo.demo.dto.image.ImageDTO;
import com.ortegainmo.demo.dto.location.LocationDTO;
import com.ortegainmo.demo.enums.OperationType;
import com.ortegainmo.demo.enums.PropertyStatus;
import com.ortegainmo.demo.enums.PropertyType;
import jakarta.validation.constraints.NotNull;

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
