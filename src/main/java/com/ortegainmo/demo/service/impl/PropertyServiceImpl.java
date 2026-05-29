package com.ortegainmo.demo.service.impl;

import com.ortegainmo.demo.dto.property.PropertyDetailDTO;
import com.ortegainmo.demo.dto.property.PropertyListDTO;
import com.ortegainmo.demo.dto.property.PropertyRequestDTO;
import com.ortegainmo.demo.enums.OperationType;
import com.ortegainmo.demo.enums.PropertyStatus;
import com.ortegainmo.demo.enums.PropertyType;
import com.ortegainmo.demo.exception.NotFoundException;
import com.ortegainmo.demo.mapper.PropertyMapper;
import com.ortegainmo.demo.model.Property;
import com.ortegainmo.demo.repository.PropertyRepository;
import com.ortegainmo.demo.service.PropertyService;
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
        // Borrado lógico (cambiamos estado) o físico, como prefieras. Acá hacemos físico por ahora.
        propertyRepository.delete(property);
    }
}
