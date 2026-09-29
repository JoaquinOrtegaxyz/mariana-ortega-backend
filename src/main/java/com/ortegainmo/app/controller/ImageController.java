package com.ortegainmo.app.controller;

import com.ortegainmo.app.dto.image.ImageDTO;
import com.ortegainmo.app.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PatchMapping("/{imageId}/set-cover/{propertyId}")
    public ResponseEntity<Void> setAsCover(@PathVariable Long propertyId, @PathVariable Long imageId) {
        imageService.setAsCover(propertyId, imageId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long imageId) {
        imageService.deleteImage(imageId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<ImageDTO>> getImagesByPropertyId(@PathVariable Long propertyId) {
        return ResponseEntity.ok(imageService.getImagesByPropertyId(propertyId));
    }

    @PostMapping("/upload/{propertyId}")
    public org.springframework.http.ResponseEntity<com.ortegainmo.app.dto.image.ImageDTO> uploadImage(
            @PathVariable Long propertyId,
            @org.springframework.web.bind.annotation.RequestParam("file") org.springframework.web.multipart.MultipartFile file) {
        return org.springframework.http.ResponseEntity.status(org.springframework.http.HttpStatus.CREATED)
                .body(imageService.uploadImage(file, propertyId));
    }

    @PostMapping("/upload-multiple/{propertyId}")
    public ResponseEntity<List<ImageDTO>> uploadMultipleImages(
            @PathVariable Long propertyId,
            @RequestParam("files") List<org.springframework.web.multipart.MultipartFile> files) {
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED)
                .body(imageService.uploadMultipleImages(files, propertyId));
    }
}
