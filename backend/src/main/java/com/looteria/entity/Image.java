package com.looteria.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "imagenes")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImagen;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_publicacion", nullable = false)
    private ListingPost publicacion;
    
    @Column(name = "ruta_imagen", nullable = false)
    private String rutaImagen;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    
    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }
}
