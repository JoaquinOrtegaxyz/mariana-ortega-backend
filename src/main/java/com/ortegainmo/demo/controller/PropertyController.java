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

    @PostMapping
    public ResponseEntity<PropertyDetailDTO> addProperty(@Valid @RequestBody PropertyRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.addProperty(dto));
    }

    @GetMapping
    public ResponseEntity<Page<PropertyListDTO>> listAvailableProperties(
            @PageableDefault(page = 0, size = 12, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(propertyService.listAvailableProperties(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PropertyListDTO>> searchProperties(
            @RequestParam(required = false) OperationType operationType,
            @RequestParam(required = false) PropertyType propertyType,
            @PageableDefault(page = 0, size = 12) Pageable pageable) {
        return ResponseEntity.ok(propertyService.searchProperties(operationType, propertyType, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDetailDTO> getPropertyById(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.getPropertyById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/archived")
    public ResponseEntity<Page<PropertyListDTO>> listArchivedProperties(
            @PageableDefault(page = 0, size = 12, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(propertyService.listArchivedProperties(pageable));
    }

    @DeleteMapping("/{id}/permanent")
    public ResponseEntity<Void> deletePropertyPermanently(@PathVariable Long id) {
        propertyService.deletePropertyPermanently(id);
        return ResponseEntity.noContent().build();
    }
}
