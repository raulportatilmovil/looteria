package com.looteria.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SearchListingDTO {
    
    private Long idPublicacion;
    private Long idProducto;
    private String titulo;
    private String descripcion;
    private String plataforma;
    private String tipoArticulo;
    private BigDecimal precio;
    private String estadoArticulo;
    private String tipoTransaccion;
    private String region;
    private String usuario;
    private String descripcionEstado;
    private String idioma;
    private LocalDateTime fechaCreacion;

    public SearchListingDTO() {}

    public SearchListingDTO(Long idPublicacion, Long idProducto, String titulo, String descripcion,
                           String plataforma, String tipoArticulo, BigDecimal precio,
                           String estadoArticulo, String tipoTransaccion, String region, String usuario,
                           String descripcionEstado, String idioma, LocalDateTime fechaCreacion) {
        this.idPublicacion = idPublicacion;
        this.idProducto = idProducto;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.plataforma = plataforma;
        this.tipoArticulo = tipoArticulo;
        this.precio = precio;
        this.estadoArticulo = estadoArticulo;
        this.tipoTransaccion = tipoTransaccion;
        this.region = region;
        this.usuario = usuario;
        this.descripcionEstado = descripcionEstado;
        this.idioma = idioma;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters
    public Long getIdPublicacion() { return idPublicacion; }
    public Long getIdProducto() { return idProducto; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public String getPlataforma() { return plataforma; }
    public String getTipoArticulo() { return tipoArticulo; }
    public BigDecimal getPrecio() { return precio; }
    public String getEstadoArticulo() { return estadoArticulo; }
    public String getTipoTransaccion() { return tipoTransaccion; }
    public String getRegion() { return region; }
    public String getUsuario() { return usuario; }
    public String getDescripcionEstado() { return descripcionEstado; }
    public String getIdioma() { return idioma; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    // Setters
    public void setIdPublicacion(Long idPublicacion) { this.idPublicacion = idPublicacion; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }
    public void setTipoArticulo(String tipoArticulo) { this.tipoArticulo = tipoArticulo; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public void setEstadoArticulo(String estadoArticulo) { this.estadoArticulo = estadoArticulo; }
    public void setTipoTransaccion(String tipoTransaccion) { this.tipoTransaccion = tipoTransaccion; }
    public void setRegion(String region) { this.region = region; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public void setDescripcionEstado(String descripcionEstado) { this.descripcionEstado = descripcionEstado; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
