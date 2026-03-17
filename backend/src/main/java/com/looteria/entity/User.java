package com.looteria.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false, unique = true)
    private String nombreUsuario;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole rol;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "puntos_acumulados", nullable = false)
    private Long puntosAcumulados = 0L;

    @Column(name = "verificado_identidad", nullable = false)
    private Boolean verificadoIdentidad = false;

    @Column(name = "reputacion_media", nullable = false, precision = 3, scale = 2)
    private BigDecimal reputacionMedia = BigDecimal.ZERO;

    // Constructores
    public User() {
    }

    public User(Long idUsuario, String email, String contrasena, String nombreUsuario, UserRole rol) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.contrasena = contrasena;
        this.nombreUsuario = nombreUsuario;
        this.rol = rol;
        this.puntosAcumulados = 0L;
        this.verificadoIdentidad = false;
        this.reputacionMedia = BigDecimal.ZERO;
    }

    // Getters y Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public UserRole getRol() {
        return rol;
    }

    public void setRol(UserRole rol) {
        this.rol = rol;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Long getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(Long puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }

    public Boolean getVerificadoIdentidad() {
        return verificadoIdentidad;
    }

    public void setVerificadoIdentidad(Boolean verificadoIdentidad) {
        this.verificadoIdentidad = verificadoIdentidad;
    }

    public BigDecimal getReputacionMedia() {
        return reputacionMedia;
    }

    public void setReputacionMedia(BigDecimal reputacionMedia) {
        this.reputacionMedia = reputacionMedia;
    }

    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
    }
}
