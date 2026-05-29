package com.ortegainmo.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "characteristics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Characteristics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer bedrooms;
    private Integer bathrooms;
    private Double totalArea;
    private Double coveredArea;
    private Boolean hasGarage;
    private Integer age;

    private Double latitude;
    private Double longitude;
}
