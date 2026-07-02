package com.ortegainmo.app.enums;

import lombok.Getter;

@Getter
public enum Zone {
    CENTRO("Centro"),
    PLAYA("Playa (Villa Díaz Vélez)"),
    PUERTO("Puerto"),
    QUEQUEN("Quequén"),
    VILLA_DEL_DEPORTISTA("Villa del Deportista"),
    PARQUE("Barrio Parque"),
    NUEVE_DE_JULIO("9 de Julio"),
    INTERMEDIA("Intermedia"),
    OTRO("Otro");

    private final String displayName;

    Zone(String displayName) {
        this.displayName = displayName;
    }
}