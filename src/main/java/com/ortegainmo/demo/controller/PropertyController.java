package com.ortegainmo.demo.controller;

import com.ortegainmo.demo.dto.property.PropertyDetailDTO;
import com.ortegainmo.demo.dto.property.PropertyListDTO;
import com.ortegainmo.demo.dto.property.PropertyRequestDTO;
import com.ortegainmo.demo.enums.OperationType;
import com.ortegainmo.demo.enums.PropertyType;
import com.ortegainmo.demo.service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    // Alta de propiedad (Panel de administración)
    @PostMapping
    public ResponseEntity<PropertyDetailDTO> addProperty(@Valid @RequestBody PropertyRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.addProperty(dto));
    }

    // Listado público de disponibles (Paginado de a 12 para la grilla del front)
    @GetMapping
    public ResponseEntity<Page<PropertyListDTO>> listAvailableProperties(
            @PageableDefault(page = 0, size = 12, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(propertyService.listAvailableProperties(pageable));
    }

    // Buscador principal con filtros opcionales (OperationType y PropertyType)
    @GetMapping("/search")
    public ResponseEntity<Page<PropertyListDTO>> searchProperties(
            @RequestParam(required = false) OperationType operationType,
            @RequestParam(required = false) PropertyType propertyType,
            @PageableDefault(page = 0, size = 12) Pageable pageable) {
        return ResponseEntity.ok(propertyService.searchProperties(operationType, propertyType, pageable));
    }

    // Detalle de una propiedad en particular al hacerle click
    @GetMapping("/{id}")
    public ResponseEntity<PropertyDetailDTO> getPropertyById(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.getPropertyById(id));
    }

    // Baja física de la propiedad
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }
}
