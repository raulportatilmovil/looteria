package com.looteria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "publicaciones")
public class ListingPost {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPublicacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private User usuario;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plataforma_id")
    private Category plataforma;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_articulo_id")
    private Category tipoArticulo;

    @Column(name = "tipo_transaccion", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType tipoTransaccion;
    
    @Column(name = "precio", precision = 10, scale = 2)
    private BigDecimal precio;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_articulo_id")
    private Category estadoArticulo;
    
    @Column(name = "descripcion_estado", columnDefinition = "TEXT")
    private String descripcionEstado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idioma_id")
    private Category idioma;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Category region;
    
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    
    @Column(name = "estado_publicacion", nullable = false)
    @Enumerated(EnumType.STRING)
    private PublicationStatus estadoPublicacion = PublicationStatus.ACTIVA;

    @Column(name = "destacado")
    private Boolean destacado = false;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }
    
    public enum TransactionType {
        VENTA,
        INTERCAMBIO
    }
    
    public enum PublicationStatus {
        ACTIVA,
        DESACTIVADA
    }
}
