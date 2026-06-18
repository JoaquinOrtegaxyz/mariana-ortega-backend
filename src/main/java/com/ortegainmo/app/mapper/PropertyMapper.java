package com.ortegainmo.app.mapper;

import com.ortegainmo.app.dto.property.*;
import com.ortegainmo.app.model.Property;
import com.ortegainmo.app.model.Image;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PropertyMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "images", ignore = true)
    Property toEntity(PropertyRequestDTO dto);

    PropertyDetailDTO toDetailDto(Property entity);

    @Mapping(target = "coverImageUrl", expression = "java(getCoverImageUrl(entity.getImages()))")
    @Mapping(target = "bedrooms", source = "characteristics.bedrooms")
    @Mapping(target = "bathrooms", source = "characteristics.bathrooms")
    @Mapping(target = "street", source = "location.street")
    @Mapping(target = "streetNumber", source = "location.streetNumber") // <--- MAPEAMOS LA ALTURA ACÁ
    PropertyListDTO toListDto(Property entity);

    default String getCoverImageUrl(List<Image> images) {
        if (images == null || images.isEmpty()) {
            return null;
        }
        return images.stream()
                .filter(Image::getIsCover)
                .map(Image::getUrl)
                .findFirst()
                .orElse(images.get(0).getUrl());
    }
}
