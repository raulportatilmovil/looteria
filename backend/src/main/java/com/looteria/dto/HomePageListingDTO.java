package com.looteria.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class HomePageListingDTO {
    private Long idPublicacion;
    private String titulo;
    private String descripcion;
    private String plataformaNombre;
    private String tipoArticuloNombre;
    private BigDecimal precio;
    private String estadoArticuloNombre;
    private String tipoTransaccion;
    private String regionNombre;
    private String nombreUsuario;
    private String descripcionEstado;
    private String idiomaNombre;
    private LocalDateTime fechaCreacion;
    private Boolean destacado;
    private List<String> imagenes;

    public HomePageListingDTO() {}

    public HomePageListingDTO(Long idPublicacion, String titulo, String descripcion,
                               String plataformaNombre, String tipoArticuloNombre, BigDecimal precio,
                               String estadoArticuloNombre, String tipoTransaccion, String regionNombre,
                               String nombreUsuario, String descripcionEstado, String idiomaNombre,
                               LocalDateTime fechaCreacion) {
        this.idPublicacion = idPublicacion;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.plataformaNombre = plataformaNombre;
        this.tipoArticuloNombre = tipoArticuloNombre;
        this.precio = precio;
        this.estadoArticuloNombre = estadoArticuloNombre;
        this.tipoTransaccion = tipoTransaccion;
        this.regionNombre = regionNombre;
        this.nombreUsuario = nombreUsuario;
        this.descripcionEstado = descripcionEstado;
        this.idiomaNombre = idiomaNombre;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters and Setters
    public Long getIdPublicacion() { return idPublicacion; }
    public void setIdPublicacion(Long idPublicacion) { this.idPublicacion = idPublicacion; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getPlataformaNombre() { return plataformaNombre; }
    public void setPlataformaNombre(String plataformaNombre) { this.plataformaNombre = plataformaNombre; }

    public String getTipoArticuloNombre() { return tipoArticuloNombre; }
    public void setTipoArticuloNombre(String tipoArticuloNombre) { this.tipoArticuloNombre = tipoArticuloNombre; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public String getEstadoArticuloNombre() { return estadoArticuloNombre; }
    public void setEstadoArticuloNombre(String estadoArticuloNombre) { this.estadoArticuloNombre = estadoArticuloNombre; }

    public String getTipoTransaccion() { return tipoTransaccion; }
    public void setTipoTransaccion(String tipoTransaccion) { this.tipoTransaccion = tipoTransaccion; }

    public String getRegionNombre() { return regionNombre; }
    public void setRegionNombre(String regionNombre) { this.regionNombre = regionNombre; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getDescripcionEstado() { return descripcionEstado; }
    public void setDescripcionEstado(String descripcionEstado) { this.descripcionEstado = descripcionEstado; }

    public String getIdiomaNombre() { return idiomaNombre; }
    public void setIdiomaNombre(String idiomaNombre) { this.idiomaNombre = idiomaNombre; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public Boolean getDestacado() { return destacado; }
    public void setDestacado(Boolean destacado) { this.destacado = destacado; }

    public List<String> getImagenes() { return imagenes; }
    public void setImagenes(List<String> imagenes) { this.imagenes = imagenes; }
}
