package com.ortegainmo.app.service.impl;

import com.ortegainmo.app.dto.property.PropertyDetailDTO;
import com.ortegainmo.app.dto.property.PropertyListDTO;
import com.ortegainmo.app.dto.property.PropertyRequestDTO;
import com.ortegainmo.app.enums.OperationType;
import com.ortegainmo.app.enums.PropertyStatus;
import com.ortegainmo.app.enums.PropertyType;
import com.ortegainmo.app.exception.NotFoundException;
import com.ortegainmo.app.mapper.PropertyMapper;
import com.ortegainmo.app.model.Property;
import com.ortegainmo.app.repository.PropertyRepository;
import com.ortegainmo.app.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    @Override
    @Transactional
    public PropertyDetailDTO addProperty(PropertyRequestDTO dto) {
        Property property = propertyMapper.toEntity(dto);
        // Por defecto arranca disponible
        property.setStatus(PropertyStatus.AVAILABLE);
        property = propertyRepository.save(property);
        return propertyMapper.toDetailDto(property);
    }

    @Override
    public Page<PropertyListDTO> listAvailableProperties(Pageable pageable) {
        Page<Property> page = propertyRepository.findByStatus(PropertyStatus.AVAILABLE, pageable);
        if(page.isEmpty()){
            throw new NotFoundException("No hay propiedades publicadas");
        }
        return page.map(propertyMapper::toListDto);
    }

    @Override
    public Page<PropertyListDTO> searchProperties(OperationType operation, PropertyType type, Pageable pageable) {
        Page<Property> page = propertyRepository.searchProperties(operation, type, pageable);
        return page.map(propertyMapper::toListDto);
    }

    @Override
    public PropertyDetailDTO getPropertyById(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La propiedad no existe"));
        return propertyMapper.toDetailDto(property);
    }

    @Override
    @Transactional
    public void deleteProperty(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La propiedad no existe"));
        // Borrado lógico: pasa a estar archivada
        property.setStatus(PropertyStatus.ARCHIVED);
        propertyRepository.save(property);
    }

    @Override
    public Page<PropertyListDTO> listArchivedProperties(Pageable pageable) {
        Page<Property> page = propertyRepository.findByStatus(PropertyStatus.ARCHIVED, pageable);
        return page.map(propertyMapper::toListDto);
    }

    @Override
    @Transactional
    public void deletePropertyPermanently(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La propiedad no existe"));

        if (property.getStatus() != PropertyStatus.ARCHIVED) {
            throw new IllegalStateException("Solo se pueden borrar permanentemente las propiedades archivadas");
        }
        propertyRepository.delete(property);
    }
}
