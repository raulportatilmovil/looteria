package com.looteria.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ListingDetailDTO {
    private Long idPublicacion;
    private Long idUsuario;
    private String nombreUsuario;
    private String email;
    private String titulo;
    private String descripcion;
    private String plataforma;
    private String tipoArticulo;
    private String franquicia;
    private String tipoTransaccion;
    private BigDecimal precio;
    private String estadoArticulo;
    private String descripcionEstado;
    private String idioma;
    private String region;
    private LocalDateTime fechaCreacion;
    private String estadoPublicacion;
    private Boolean envio;

    public ListingDetailDTO() {
    }

    public ListingDetailDTO(Long idPublicacion, Long idUsuario, String nombreUsuario, String email,
                           String titulo, String descripcion, String plataforma, String tipoArticulo,
                           String franquicia, String tipoTransaccion, BigDecimal precio,
                           String estadoArticulo, String descripcionEstado, String idioma,
                           String region, LocalDateTime fechaCreacion, String estadoPublicacion, Boolean envio) {
        this.idPublicacion = idPublicacion;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.plataforma = plataforma;
        this.tipoArticulo = tipoArticulo;
        this.franquicia = franquicia;
        this.tipoTransaccion = tipoTransaccion;
        this.precio = precio;
        this.estadoArticulo = estadoArticulo;
        this.descripcionEstado = descripcionEstado;
        this.idioma = idioma;
        this.region = region;
        this.fechaCreacion = fechaCreacion;
        this.estadoPublicacion = estadoPublicacion;
        this.envio = envio;
    }

    public Long getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Long idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(String tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
    }

    public String getFranquicia() {
        return franquicia;
    }

    public void setFranquicia(String franquicia) {
        this.franquicia = franquicia;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getEstadoArticulo() {
        return estadoArticulo;
    }

    public void setEstadoArticulo(String estadoArticulo) {
        this.estadoArticulo = estadoArticulo;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstadoPublicacion() {
        return estadoPublicacion;
    }

    public void setEstadoPublicacion(String estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
    }

    public Boolean getEnvio() {
        return envio;
    }

    public void setEnvio(Boolean envio) {
        this.envio = envio;
    }
}
