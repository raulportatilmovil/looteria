package com.looteria.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "verificaciones")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Verification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVerificacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transaccion", nullable = false)
    private Transaction transaccion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_publicacion", nullable = false)
    private ListingPost publicacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_admin_verificador")
    private User adminVerificador;
    
    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private VerificationStatus estado = VerificationStatus.PENDIENTE;
    
    @Column(name = "fecha_solicitud", nullable = false, updatable = false)
    private LocalDateTime fechaSolicitud;
    
    @Column(name = "fecha_respuesta")
    private LocalDateTime fechaRespuesta;
    
    @Column(name = "comentario_admin", columnDefinition = "TEXT")
    private String comentarioAdmin;
    
    @PrePersist
    protected void onCreate() {
        fechaSolicitud = LocalDateTime.now();
    }
    
    public enum VerificationStatus {
        PENDIENTE,
        APROBADA,
        RECHAZADA,
        CANCELADA
    }
}
