package com.looteria.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductDTO {
    
    private Long idProducto;
    private String titulo;
    private String descripcion;
    private Long plataformaId;
    private String plataformaNombre;
    private Long tipoArticuloId;
    private String tipoArticuloNombre;
    private LocalDateTime fechaLanzamiento;
    private BigDecimal valorEstimado;
    private LocalDateTime fechaCreacion;

    public ProductDTO() {}

    public ProductDTO(Long idProducto, String titulo, String descripcion, Long plataformaId,
                     String plataformaNombre, Long tipoArticuloId, String tipoArticuloNombre,
                     LocalDateTime fechaLanzamiento,
                     BigDecimal valorEstimado, LocalDateTime fechaCreacion) {
        this.idProducto = idProducto;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.plataformaId = plataformaId;
        this.plataformaNombre = plataformaNombre;
        this.tipoArticuloId = tipoArticuloId;
        this.tipoArticuloNombre = tipoArticuloNombre;
        this.fechaLanzamiento = fechaLanzamiento;
        this.valorEstimado = valorEstimado;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdProducto() { return idProducto; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Long getPlataformaId() { return plataformaId; }
    public void setPlataformaId(Long plataformaId) { this.plataformaId = plataformaId; }
    public String getPlataformaNombre() { return plataformaNombre; }
    public void setPlataformaNombre(String plataformaNombre) { this.plataformaNombre = plataformaNombre; }
    public Long getTipoArticuloId() { return tipoArticuloId; }
    public void setTipoArticuloId(Long tipoArticuloId) { this.tipoArticuloId = tipoArticuloId; }
    public String getTipoArticuloNombre() { return tipoArticuloNombre; }
    public void setTipoArticuloNombre(String tipoArticuloNombre) { this.tipoArticuloNombre = tipoArticuloNombre; }
    public LocalDateTime getFechaLanzamiento() { return fechaLanzamiento; }
    public void setFechaLanzamiento(LocalDateTime fechaLanzamiento) { this.fechaLanzamiento = fechaLanzamiento; }
    public BigDecimal getValorEstimado() { return valorEstimado; }
    public void setValorEstimado(BigDecimal valorEstimado) { this.valorEstimado = valorEstimado; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
