package com.ortegainmo.demo.service;

import com.ortegainmo.demo.dto.property.PropertyDetailDTO;
import com.ortegainmo.demo.dto.property.PropertyListDTO;
import com.ortegainmo.demo.dto.property.PropertyRequestDTO;
import com.ortegainmo.demo.enums.OperationType;
import com.ortegainmo.demo.enums.PropertyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PropertyService {
    PropertyDetailDTO addProperty(PropertyRequestDTO dto);
    Page<PropertyListDTO> listAvailableProperties(Pageable pageable);
    Page<PropertyListDTO> searchProperties(OperationType operation, PropertyType type, Pageable pageable);
    PropertyDetailDTO getPropertyById(Long id);

    void deleteProperty(Long id);

    Page<PropertyListDTO> listArchivedProperties(Pageable pageable);

    void deletePropertyPermanently(Long id);
}
