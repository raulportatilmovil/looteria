package com.looteria.dto;

import java.math.BigDecimal;

public class CreateListingRequestDTO {
    private Long userId;
    private String producto;
    private String descripcion;
    private String plataforma;
    private String tipoTransaccion;
    private BigDecimal precio;
    private String estado;
    private String descripcionEstado;
    private String especificaciones;
    private String idioma;
    private String region;

    public CreateListingRequestDTO() {
    }

    public CreateListingRequestDTO(Long userId, String producto, String descripcion, String plataforma,
                                    String tipoTransaccion, BigDecimal precio, String estado,
                                    String descripcionEstado, String especificaciones,
                                    String idioma, String region) {
        this.userId = userId;
        this.producto = producto;
        this.descripcion = descripcion;
        this.plataforma = plataforma;
        this.tipoTransaccion = tipoTransaccion;
        this.precio = precio;
        this.estado = estado;
        this.descripcionEstado = descripcionEstado;
        this.especificaciones = especificaciones;
        this.idioma = idioma;
        this.region = region;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    public String getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
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
}
