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

public interface PropertyRepository extends JpaRepository<Property, Long> {

    @EntityGraph(attributePaths = {"location", "characteristics", "images"})
    Page<Property> findByStatus(PropertyStatus status, Pageable pageable);

    @EntityGraph(attributePaths = {"location", "characteristics", "images"})
    @Query("SELECT p FROM Property p WHERE p.status = 'AVAILABLE' " +
            "AND (:operation IS NULL OR p.operationType = :operation) " +
            "AND (:type IS NULL OR p.propertyType = :type) " +
            "AND (:zone IS NULL OR p.location.zone = :zone) " +
            "AND (:bedrooms IS NULL OR p.characteristics.bedrooms >= :bedrooms) " +
            "AND (:bathrooms IS NULL OR p.characteristics.bathrooms >= :bathrooms)")
    Page<Property> searchAdvanced(
            @Param("operation") OperationType operation,
            @Param("type") PropertyType type,
            @Param("zone") Zone zone,
            @Param("bedrooms") Integer bedrooms,
            @Param("bathrooms") Integer bathrooms,
            Pageable pageable);
}