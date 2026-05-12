package com.looteria.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "verificaciones")
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
    private VerificationStatus estado = VerificationStatus.PENDIENTE;
    
    @Column(name = "fecha_solicitud", nullable = false, updatable = false)
    private LocalDateTime fechaSolicitud;
    
    @Column(name = "fecha_respuesta")
    private LocalDateTime fechaRespuesta;
    
    @Column(name = "comentario_admin", columnDefinition = "TEXT")
    private String comentarioAdmin;

    public Verification() {
    }

    public Verification(Long idVerificacion, Transaction transaccion, ListingPost publicacion,
                       User adminVerificador, VerificationStatus estado, LocalDateTime fechaSolicitud,
                       LocalDateTime fechaRespuesta, String comentarioAdmin) {
        this.idVerificacion = idVerificacion;
        this.transaccion = transaccion;
        this.publicacion = publicacion;
        this.adminVerificador = adminVerificador;
        this.estado = estado;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaRespuesta = fechaRespuesta;
        this.comentarioAdmin = comentarioAdmin;
    }

    public Long getIdVerificacion() {
        return idVerificacion;
    }

    public void setIdVerificacion(Long idVerificacion) {
        this.idVerificacion = idVerificacion;
    }

    public Transaction getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaction transaccion) {
        this.transaccion = transaccion;
    }

    public ListingPost getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(ListingPost publicacion) {
        this.publicacion = publicacion;
    }

    public User getAdminVerificador() {
        return adminVerificador;
    }

    public void setAdminVerificador(User adminVerificador) {
        this.adminVerificador = adminVerificador;
    }

    public VerificationStatus getEstado() {
        return estado;
    }

    public void setEstado(VerificationStatus estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public LocalDateTime getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(LocalDateTime fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public String getComentarioAdmin() {
        return comentarioAdmin;
    }

    public void setComentarioAdmin(String comentarioAdmin) {
        this.comentarioAdmin = comentarioAdmin;
    }
    
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
