package com.ortegainmo.app.model;

import com.ortegainmo.app.enums.Zone;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "locations", indexes = {@Index(name = "idx_locations_zone", columnList = "zone")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String streetNumber;

    @Enumerated(EnumType.STRING)
    private Zone zone;

    private String floor;
    private String apartment;
}

