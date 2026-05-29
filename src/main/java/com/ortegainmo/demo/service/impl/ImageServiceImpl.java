package com.ortegainmo.demo.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ortegainmo.demo.dto.image.ImageDTO;
import com.ortegainmo.demo.exception.NotFoundException;
import com.ortegainmo.demo.model.Image;
import com.ortegainmo.demo.model.Property;
import com.ortegainmo.demo.repository.ImageRepository;
import com.ortegainmo.demo.repository.PropertyRepository;
import com.ortegainmo.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final PropertyRepository propertyRepository;
    private final Cloudinary cloudinary;

    @Override
    @Transactional
    public ImageDTO uploadImage(MultipartFile file, Long propertyId) {
        // 1. Validamos que la propiedad exista en la DB
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new NotFoundException("La propiedad no existe"));

        try {
            // 2. Subimos el archivo físico a Cloudinary
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

            // 3. Rescatamos la URL que nos generó la nube
            String imageUrl = uploadResult.get("secure_url").toString();

            // 4. Si es la primera foto que se sube, la marcamos como portada
            boolean isCover = imageRepository.findByPropertyId(propertyId).isEmpty();

            // 5. Guardamos en PostgreSQL
            Image image = Image.builder()
                    .url(imageUrl)
                    .isCover(isCover)
                    .property(property)
                    .build();

            image = imageRepository.save(image);

            return new ImageDTO(image.getId(), image.getUrl(), image.getIsCover());

        } catch (IOException e) {
            throw new RuntimeException("Error al subir la imagen a la nube");
        }
    }

    @Override
    @Transactional
    public void setAsCover(Long propertyId, Long imageId) {
        List<Image> propertyImages = imageRepository.findByPropertyId(propertyId);
        if (propertyImages.isEmpty()) throw new NotFoundException("No hay imágenes");

        boolean imageExists = false;
        for (Image img : propertyImages) {
            if (img.getId().equals(imageId)) {
                img.setIsCover(true);
                imageExists = true;
            } else {
                img.setIsCover(false);
            }
        }
        if (!imageExists) throw new NotFoundException("La imagen no pertenece a la propiedad");
        imageRepository.saveAll(propertyImages);
    }

    @Override
    @Transactional
    public void deleteImage(Long imageId) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new NotFoundException("La imagen no existe"));
        imageRepository.delete(image);
    }

    @Override
    public List<ImageDTO> getImagesByPropertyId(Long propertyId) {
        return imageRepository.findByPropertyId(propertyId).stream()
                .map(img -> new ImageDTO(img.getId(), img.getUrl(), img.getIsCover()))
                .collect(Collectors.toList());
    }
}
