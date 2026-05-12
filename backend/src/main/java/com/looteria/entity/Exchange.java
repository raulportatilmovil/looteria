package com.looteria.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "intercambios")
public class Exchange {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIntercambio;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_publicacion", nullable = false)
    private ListingPost publicacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitante", nullable = false)
    private User solicitante;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitado", nullable = false)
    private User solicitado;
    
    @Column(name = "mensaje", columnDefinition = "TEXT")
    private String mensaje;
    
    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private ExchangeStatus estado = ExchangeStatus.PENDIENTE;
    
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    public Exchange() {}

    public Exchange(Long idIntercambio, ListingPost publicacion, User solicitante, 
                   User solicitado, String mensaje, ExchangeStatus estado, LocalDateTime fechaCreacion) {
        this.idIntercambio = idIntercambio;
        this.publicacion = publicacion;
        this.solicitante = solicitante;
        this.solicitado = solicitado;
        this.mensaje = mensaje;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdIntercambio() {
        return idIntercambio;
    }

    public void setIdIntercambio(Long idIntercambio) {
        this.idIntercambio = idIntercambio;
    }

    public ListingPost getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(ListingPost publicacion) {
        this.publicacion = publicacion;
    }

    public User getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(User solicitante) {
        this.solicitante = solicitante;
    }

    public User getSolicitado() {
        return solicitado;
    }

    public void setSolicitado(User solicitado) {
        this.solicitado = solicitado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ExchangeStatus getEstado() {
        return estado;
    }

    public void setEstado(ExchangeStatus estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }

    public enum ExchangeStatus {
        PENDIENTE,
        ACEPTADA,
        RECHAZADA,
        CANCELADA
    }
}
