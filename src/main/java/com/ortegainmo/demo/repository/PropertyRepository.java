package com.ortegainmo.demo.repository;

import com.ortegainmo.demo.model.Property;
import com.ortegainmo.demo.enums.OperationType;
import com.ortegainmo.demo.enums.PropertyType;
import com.ortegainmo.demo.enums.PropertyStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    // El buscador principal de la página. Trae solo propiedades publicadas/disponibles.
    @Query("SELECT p FROM Property p WHERE " +
            "(:operation IS NULL OR p.operationType = :operation) AND " +
            "(:type IS NULL OR p.propertyType = :type) AND " +
            "p.status = 'AVAILABLE'")
    Page<Property> searchProperties(@Param("operation") OperationType operation,
                                    @Param("type") PropertyType type,
                                    Pageable pageable);

    // Para mostrar las propiedades destacadas en la home
    Page<Property> findByStatus(PropertyStatus status, Pageable pageable);
}