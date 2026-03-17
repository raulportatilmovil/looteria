package com.looteria.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resenas")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResena;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transaccion", nullable = false)
    private Transaction transaccion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor", nullable = false)
    private User autor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receptor", nullable = false)
    private User receptor;
    
    @Column(name = "puntuacion", nullable = false)
    private Integer puntuacion;
    
    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;
    
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    
    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }
}
