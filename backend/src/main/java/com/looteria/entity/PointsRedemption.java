package com.looteria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "canjes_puntos")
public class PointsRedemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCanje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private User usuario;

    @Column(name = "puntos_usados", nullable = false)
    private Integer puntosUsados;

    @Column(name = "tipo_canje", nullable = false)
    private String tipoCanje;

    @Column(name = "codigo", nullable = false, unique = true, length = 20)
    private String codigo;

    @Column(name = "usado", nullable = false)
    private Boolean usado = false;

    @Column(name = "fecha_canje", nullable = false, updatable = false)
    private LocalDateTime fechaCanje;

    @PrePersist
    protected void onCreate() {
        fechaCanje = LocalDateTime.now();
    }
}
