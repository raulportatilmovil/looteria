package com.looteria.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "productos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plataforma_id")
    private Category plataforma;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_articulo_id")
    private Category tipoArticulo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franquicia_id")
    private Category franquicia;
    
    @Column(name = "fecha_lanzamiento")
    private LocalDateTime fechaLanzamiento;
    
    @Column(name = "valor_estimado", precision = 10, scale = 2)
    private BigDecimal valorEstimado;
    
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    
    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }

    // Getters
    public Long getIdProducto() {
        return idProducto;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Category getPlataforma() {
        return plataforma;
    }

    public Category getTipoArticulo() {
        return tipoArticulo;
    }

    public Category getFranquicia() {
        return franquicia;
    }

    public LocalDateTime getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public BigDecimal getValorEstimado() {
        return valorEstimado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    // Setters
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPlataforma(Category plataforma) {
        this.plataforma = plataforma;
    }

    public void setTipoArticulo(Category tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
    }

    public void setFranquicia(Category franquicia) {
        this.franquicia = franquicia;
    }

    public void setFechaLanzamiento(LocalDateTime fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public void setValorEstimado(BigDecimal valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
