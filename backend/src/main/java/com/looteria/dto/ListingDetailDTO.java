package com.looteria.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ListingDetailDTO {

    // Publicación
    private Long idPublicacion;
    private String tipoTransaccion;
    private BigDecimal precio;
    private String descripcionEstado;
    private LocalDateTime fechaCreacion;
    private String estadoPublicacion;
    private Boolean envio;

    // Categorías
    private String estadoArticulo;
    private String estado;          
    private String idioma;
    private String region;

    // Producto
    private String titulo;
    private String producto;        
    private String descripcion;
    private String especificaciones; 
    private String plataforma;
    private String tipoArticulo;
    private LocalDateTime fechaLanzamiento;

    // Usuario
    private Long idUsuario;
    private Long usuarioId;         
    private String nombreUsuario;
    private String usuarioNombre;  
    private String email;
    private String usuarioEmail;     
    private String usuarioUbicacion;
    private BigDecimal usuarioReputacion;
    private Long usuarioVentas;

    // Imágenes
    private List<String> imagenes;

    // Para reseñas
    private Long transaccionId;

    public ListingDetailDTO() {
    }

    // ─── Getters y Setters ───────────────────────────────────────────────────────

    public Long getIdPublicacion() { return idPublicacion; }
    public void setIdPublicacion(Long idPublicacion) { this.idPublicacion = idPublicacion; }

    public String getTipoTransaccion() { return tipoTransaccion; }
    public void setTipoTransaccion(String tipoTransaccion) { this.tipoTransaccion = tipoTransaccion; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public String getDescripcionEstado() { return descripcionEstado; }
    public void setDescripcionEstado(String descripcionEstado) { this.descripcionEstado = descripcionEstado; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getEstadoPublicacion() { return estadoPublicacion; }
    public void setEstadoPublicacion(String estadoPublicacion) { this.estadoPublicacion = estadoPublicacion; }

    public Boolean getEnvio() { return envio; }
    public void setEnvio(Boolean envio) { this.envio = envio; }

    public String getEstadoArticulo() { return estadoArticulo; }
    public void setEstadoArticulo(String estadoArticulo) { this.estadoArticulo = estadoArticulo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEspecificaciones() { return especificaciones; }
    public void setEspecificaciones(String especificaciones) { this.especificaciones = especificaciones; }

    public String getPlataforma() { return plataforma; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }

    public String getTipoArticulo() { return tipoArticulo; }
    public void setTipoArticulo(String tipoArticulo) { this.tipoArticulo = tipoArticulo; }

    public LocalDateTime getFechaLanzamiento() { return fechaLanzamiento; }
    public void setFechaLanzamiento(LocalDateTime fechaLanzamiento) { this.fechaLanzamiento = fechaLanzamiento; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getUsuarioNombre() { return usuarioNombre; }
    public void setUsuarioNombre(String usuarioNombre) { this.usuarioNombre = usuarioNombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsuarioEmail() { return usuarioEmail; }
    public void setUsuarioEmail(String usuarioEmail) { this.usuarioEmail = usuarioEmail; }

    public String getUsuarioUbicacion() { return usuarioUbicacion; }
    public void setUsuarioUbicacion(String usuarioUbicacion) { this.usuarioUbicacion = usuarioUbicacion; }

    public BigDecimal getUsuarioReputacion() { return usuarioReputacion; }
    public void setUsuarioReputacion(BigDecimal usuarioReputacion) { this.usuarioReputacion = usuarioReputacion; }

    public Long getUsuarioVentas() { return usuarioVentas; }
    public void setUsuarioVentas(Long usuarioVentas) { this.usuarioVentas = usuarioVentas; }

    public List<String> getImagenes() { return imagenes; }
    public void setImagenes(List<String> imagenes) { this.imagenes = imagenes; }

    public Long getTransaccionId() { return transaccionId; }
    public void setTransaccionId(Long transaccionId) { this.transaccionId = transaccionId; }
}