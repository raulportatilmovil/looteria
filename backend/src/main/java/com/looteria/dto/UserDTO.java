package com.looteria.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UserDTO {
    private Long idUsuario;
    private String email;
    private String nombreUsuario;
    private String rol;
    private LocalDateTime fechaRegistro;
    private String ubicacion;
    private Long puntosAcumulados;
    private Boolean verificadoIdentidad;
    private BigDecimal reputacionMedia;

    public UserDTO() {
    }

    public UserDTO(Long idUsuario, String email, String nombreUsuario, String rol, LocalDateTime fechaRegistro, 
                   String ubicacion, Long puntosAcumulados, Boolean verificadoIdentidad, BigDecimal reputacionMedia) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
        this.ubicacion = ubicacion;
        this.puntosAcumulados = puntosAcumulados;
        this.verificadoIdentidad = verificadoIdentidad;
        this.reputacionMedia = reputacionMedia;
    }

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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
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
}
