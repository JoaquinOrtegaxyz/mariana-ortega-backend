package com.ortegainmo.demo.controller;

import com.ortegainmo.demo.dto.image.ImageDTO;
import com.ortegainmo.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    // Cambiar la foto de portada con un botón rápido
    @PatchMapping("/{imageId}/set-cover/{propertyId}")
    public ResponseEntity<Void> setAsCover(@PathVariable Long propertyId, @PathVariable Long imageId) {
        imageService.setAsCover(propertyId, imageId);
        return ResponseEntity.ok().build();
    }

    // Borrar una imagen específica de la galería
    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long imageId) {
        imageService.deleteImage(imageId);
        return ResponseEntity.noContent().build();
    }

    // Traer la galería completa de fotos asociadas a un inmueble
    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<ImageDTO>> getImagesByPropertyId(@PathVariable Long propertyId) {
        return ResponseEntity.ok(imageService.getImagesByPropertyId(propertyId));
    }

    @PostMapping("/upload/{propertyId}")
    public org.springframework.http.ResponseEntity<com.ortegainmo.demo.dto.image.ImageDTO> uploadImage(
            @PathVariable Long propertyId,
            @org.springframework.web.bind.annotation.RequestParam("file") org.springframework.web.multipart.MultipartFile file) {
        return org.springframework.http.ResponseEntity.status(org.springframework.http.HttpStatus.CREATED)
                .body(imageService.uploadImage(file, propertyId));
    }
}
