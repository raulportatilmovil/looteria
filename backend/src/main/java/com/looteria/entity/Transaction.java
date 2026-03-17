package com.looteria.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacciones")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaccion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_publicacion", nullable = false)
    private ListingPost publicacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comprador", nullable = false)
    private User comprador;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vendedor", nullable = false)
    private User vendedor;
    
    @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType tipo;
    
    @Column(name = "precio_final", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioFinal;
    
    @Column(name = "fecha_transaccion", nullable = false, updatable = false)
    private LocalDateTime fechaTransaccion;
    
    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TransactionStatus estado = TransactionStatus.PENDIENTE;
    
    @Column(name = "comision", precision = 10, scale = 2)
    private BigDecimal comision;
    
    @PrePersist
    protected void onCreate() {
        fechaTransaccion = LocalDateTime.now();
    }
    
    public enum TransactionType {
        VENTA,
        INTERCAMBIO
    }
    
    public enum TransactionStatus {
        PENDIENTE,
        COMPLETADA,
        CANCELADA,
        DEVUELTA
    }
}
