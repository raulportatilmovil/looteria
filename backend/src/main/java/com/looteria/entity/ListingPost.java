package com.looteria.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "publicaciones")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListingPost {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPublicacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private User usuario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private Product producto;
    
    @Column(name = "tipo_transaccion", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType tipoTransaccion;
    
    @Column(name = "precio", precision = 10, scale = 2)
    private java.math.BigDecimal precio;
    
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
    @Builder.Default
    private PublicationStatus estadoPublicacion = PublicationStatus.ACTIVA;
    
    @Column(name = "envio", nullable = false)
    @Builder.Default
    private Boolean envio = false;
    
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
        VENDIDA,
        CANCELADA,
        PAUSADA
    }
}
