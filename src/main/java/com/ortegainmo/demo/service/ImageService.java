package com.ortegainmo.demo.service;

import com.ortegainmo.demo.dto.image.ImageDTO;
import java.util.List;

public interface ImageService {
    // Setea una imagen específica como la portada de la propiedad
    void setAsCover(Long propertyId, Long imageId);

    // Borra una imagen de la base de datos
    void deleteImage(Long imageId);

    // Trae todas las fotos de una propiedad
    List<ImageDTO> getImagesByPropertyId(Long propertyId);

    com.ortegainmo.demo.dto.image.ImageDTO uploadImage(org.springframework.web.multipart.MultipartFile file, Long propertyId);
}