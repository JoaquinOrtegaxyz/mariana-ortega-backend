package com.ortegainmo.app.service;

import com.ortegainmo.app.dto.property.PropertyDetailDTO;
import com.ortegainmo.app.dto.property.PropertyListDTO;
import com.ortegainmo.app.dto.property.PropertyRequestDTO;
import com.ortegainmo.app.enums.OperationType;
import com.ortegainmo.app.enums.PropertyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface PropertyService {
    PropertyDetailDTO addProperty(PropertyRequestDTO dto);

    Page<PropertyListDTO> listAvailableProperties(Pageable pageable);

    // ACÁ LE AGREGAMOS LOS DORMITORIOS Y BAÑOS
    Page<PropertyListDTO> searchProperties(OperationType operation, PropertyType type, Integer bedrooms, Integer bathrooms, Pageable pageable);

    PropertyDetailDTO getPropertyById(Long id);
    void deleteProperty(Long id);
    Page<PropertyListDTO> listArchivedProperties(Pageable pageable);
    void deletePropertyPermanently(Long id);
    PropertyDetailDTO uploadPropertyImage(Long id, MultipartFile file, Boolean isCover);
    PropertyDetailDTO updateProperty(Long id, PropertyRequestDTO dto);
    void unarchiveProperty(Long id);
}
