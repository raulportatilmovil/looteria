package com.looteria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    @ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "plataforma_id")
private Category plataforma;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "tipo_articulo_id")
private Category tipoArticulo;
    
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
}
