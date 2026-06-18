package com.ortegainmo.app.controller;

import com.ortegainmo.app.dto.property.PropertyDetailDTO;
import com.ortegainmo.app.dto.property.PropertyListDTO;
import com.ortegainmo.app.dto.property.PropertyRequestDTO;
import com.ortegainmo.app.enums.OperationType;
import com.ortegainmo.app.enums.PropertyType;
import com.ortegainmo.app.service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "http://localhost:4200")
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

    @PostMapping(value = "/{id}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<com.ortegainmo.app.dto.property.PropertyDetailDTO> uploadImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "isCover", defaultValue = "false") Boolean isCover) {

        return ResponseEntity.ok(propertyService.uploadPropertyImage(id, file, isCover));
    }

    @PutMapping("/{id}")
    public org.springframework.http.ResponseEntity<PropertyDetailDTO> updateProperty(
            @PathVariable Long id,
            @RequestBody PropertyRequestDTO dto) {
        return ResponseEntity.ok(propertyService.updateProperty(id, dto));
    }

    @PutMapping("/{id}/unarchive")
    public ResponseEntity<Void> unarchiveProperty(@PathVariable Long id) {
        propertyService.unarchiveProperty(id);
        return ResponseEntity.ok().build();
    }
}
