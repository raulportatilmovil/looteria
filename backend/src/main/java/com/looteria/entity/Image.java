package com.looteria.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "imagenes")
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

    public Image() {
    }

    public Image(Long idImagen, ListingPost publicacion, String rutaImagen, String descripcion, LocalDateTime fechaCreacion) {
        this.idImagen = idImagen;
        this.publicacion = publicacion;
        this.rutaImagen = rutaImagen;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Long idImagen) {
        this.idImagen = idImagen;
    }

    public ListingPost getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(ListingPost publicacion) {
        this.publicacion = publicacion;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }
}
