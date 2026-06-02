package com.ortegainmo.app.repository;

import com.ortegainmo.app.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    // Nos trae todas las imágenes de una propiedad específica
    List<Image> findByPropertyId(Long propertyId);
}
