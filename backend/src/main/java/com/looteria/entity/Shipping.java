package com.looteria.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "envios")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipping {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnvio;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transaccion", nullable = false)
    private Transaction transaccion;
    
    @Column(name = "proveedor", nullable = false)
    private String proveedor;
    
    @Column(name = "numero_seguimiento", unique = true)
    private String numeroSeguimiento;
    
    @Column(name = "detalles_seguimiento", columnDefinition = "TEXT")
    private String detallesSeguimiento;
    
    @Column(name = "coste", precision = 10, scale = 2)
    private BigDecimal coste;
    
    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ShippingStatus estado = ShippingStatus.PREPARANDO;
    
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    
    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }
    
    public enum ShippingStatus {
        PREPARANDO,
        ENVIADO,
        EN_TRANSITO,
        ENTREGADO,
        DEVUELTO
    }
}
