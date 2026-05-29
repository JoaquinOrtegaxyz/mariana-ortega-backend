package com.ortegainmo.demo.mapper;

import com.ortegainmo.demo.dto.property.*;
import com.ortegainmo.demo.model.Property;
import com.ortegainmo.demo.model.Image;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PropertyMapper {

    // De Request a Entidad
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "images", ignore = true)
    Property toEntity(PropertyRequestDTO dto);

    // De Entidad a Detalle completo
    PropertyDetailDTO toDetailDto(Property entity);

    // De Entidad a DTO de Lista (para las cards)
    @Mapping(target = "coverImageUrl", expression = "java(getCoverImageUrl(entity.getImages()))")
    @Mapping(target = "bedrooms", source = "characteristics.bedrooms")
    @Mapping(target = "bathrooms", source = "characteristics.bathrooms")
    @Mapping(target = "street", source = "location.street")
    PropertyListDTO toListDto(Property entity);

    // Método custom para extraer solo la portada y mandarla a la tarjeta
    default String getCoverImageUrl(List<Image> images) {
        if (images == null || images.isEmpty()) {
            return null; // O podés poner una URL de "no-image-available.jpg" por defecto
        }
        return images.stream()
                .filter(Image::getIsCover)
                .map(Image::getUrl)
                .findFirst()
                .orElse(images.get(0).getUrl()); // Si se olvidó de poner portada, tira la primera
    }
}
