package com.ortegainmo.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private Boolean isCover;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    // Usamos StringBuilder para evitar el bucle infinito al imprimir la entidad por consola
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Image{");
        sb.append("id=").append(id);
        sb.append(", url='").append(url).append('\'');
        sb.append(", isCover=").append(isCover);
        sb.append('}');
        return sb.toString();
    }
}
