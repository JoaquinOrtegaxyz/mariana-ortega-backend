package com.ortegainmo.app.repository;

import com.ortegainmo.app.enums.OperationType;
import com.ortegainmo.app.enums.PropertyStatus;
import com.ortegainmo.app.enums.PropertyType;
import com.ortegainmo.app.enums.Zone;
import com.ortegainmo.app.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Override
    @EntityGraph(attributePaths = {"location", "characteristics", "images"})
    Optional<Property> findById(Long id);

    @EntityGraph(attributePaths = {"location", "characteristics", "images"})
    Page<Property> findByStatus(PropertyStatus status, Pageable pageable);

    @EntityGraph(attributePaths = {"location", "characteristics", "images"})
    @Query("SELECT p FROM Property p " +
            "LEFT JOIN p.location loc " +
            "LEFT JOIN p.characteristics c " +
            "WHERE p.status = 'AVAILABLE' " +
            "AND (:operation IS NULL OR p.operationType = :operation) " +
            "AND (:type IS NULL OR p.propertyType = :type) " +
            "AND (:zone IS NULL OR loc.zone = :zone) " +
            "AND (:bedrooms IS NULL OR c.bedrooms >= :bedrooms) " +
            "AND (:bathrooms IS NULL OR c.bathrooms >= :bathrooms) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice)")
    Page<Property> searchAdvanced(
            @Param("operation") OperationType operation,
            @Param("type") PropertyType type,
            @Param("zone") Zone zone,
            @Param("bedrooms") Integer bedrooms,
            @Param("bathrooms") Integer bathrooms,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            Pageable pageable);
}