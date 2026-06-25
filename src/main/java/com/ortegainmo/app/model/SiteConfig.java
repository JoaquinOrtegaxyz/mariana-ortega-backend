package com.ortegainmo.app.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "site_config")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String whatsapp;
    private String instagram;
    private String facebook;
}
