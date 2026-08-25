package com.ortegainmo.app.controller;

import com.ortegainmo.app.dto.property.PropertyDetailDTO;
import com.ortegainmo.app.dto.property.PropertyListDTO;
import com.ortegainmo.app.dto.property.PropertyRequestDTO;
import com.ortegainmo.app.enums.OperationType;
import com.ortegainmo.app.enums.PropertyType;
import com.ortegainmo.app.enums.Zone;
import com.ortegainmo.app.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
@Tag(name = "Propiedades", description = "GestiÃ³n y bÃºsqueda avanzada de propiedades inmobiliarias")
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping
    @Operation(summary = "Crear nueva propiedad")
    public ResponseEntity<PropertyDetailDTO> addProperty(@Valid @RequestBody PropertyRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.addProperty(dto));
    }

    @GetMapping
    @Operation(summary = "Listar propiedades disponibles paginadas")
    public ResponseEntity<Page<PropertyListDTO>> listAvailableProperties(
            @PageableDefault(page = 0, size = 12, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(propertyService.listAvailableProperties(pageable));
    }

    @GetMapping("/search")
    @Operation(summary = "BÃºsqueda avanzada de propiedades filtradas por tipo, operaciÃ³n, zona y ambientes")
    public ResponseEntity<Page<PropertyListDTO>> searchProperties(
            @RequestParam(required = false) OperationType operationType,
            @RequestParam(required = false) PropertyType propertyType,
            @RequestParam(required = false) Zone zone,
            @RequestParam(required = false) Integer bedrooms,
            @RequestParam(required = false) Integer bathrooms,
            @PageableDefault(page = 0, size = 12) Pageable pageable) {
        return ResponseEntity.ok(propertyService.searchProperties(operationType, propertyType, zone, bedrooms, bathrooms, pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener detalle completo de una propiedad por su ID")
    public ResponseEntity<PropertyDetailDTO> getPropertyById(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.getPropertyById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Archivar una propiedad")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/archived")
    @Operation(summary = "Listar propiedades archivadas")
    public ResponseEntity<Page<PropertyListDTO>> listArchivedProperties(
            @PageableDefault(page = 0, size = 12, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(propertyService.listArchivedProperties(pageable));
    }

    @DeleteMapping("/{id}/permanent")
    @Operation(summary = "Eliminar permanentemente una propiedad archivada")
    public ResponseEntity<Void> deletePropertyPermanently(@PathVariable Long id) {
        propertyService.deletePropertyPermanently(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Subir imagen a una propiedad")
    public ResponseEntity<PropertyDetailDTO> uploadImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "isCover", defaultValue = "false") Boolean isCover) {

        return ResponseEntity.ok(propertyService.uploadPropertyImage(id, file, isCover));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos de una propiedad")
    public ResponseEntity<PropertyDetailDTO> updateProperty(
            @PathVariable Long id,
            @RequestBody PropertyRequestDTO dto) {
        return ResponseEntity.ok(propertyService.updateProperty(id, dto));
    }

    @PutMapping("/{id}/unarchive")
    @Operation(summary = "Restaurar una propiedad archivada")
    public ResponseEntity<Void> unarchiveProperty(@PathVariable Long id) {
        propertyService.unarchiveProperty(id);
        return ResponseEntity.ok().build();
    }
}