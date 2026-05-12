package com.looteria.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UpdateProductRequest {
    
    private String titulo;
    private String descripcion;
    private Long plataformaId;
    private Long tipoArticuloId;
    private LocalDateTime fechaLanzamiento;
    private BigDecimal valorEstimado;

    public UpdateProductRequest() {
    }

    public UpdateProductRequest(String titulo, String descripcion, Long plataformaId, 
                                Long tipoArticuloId,
                                LocalDateTime fechaLanzamiento, BigDecimal valorEstimado) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.plataformaId = plataformaId;
        this.tipoArticuloId = tipoArticuloId;
        this.fechaLanzamiento = fechaLanzamiento;
        this.valorEstimado = valorEstimado;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Long getPlataformaId() { return plataformaId; }
    public void setPlataformaId(Long plataformaId) { this.plataformaId = plataformaId; }
    public Long getTipoArticuloId() { return tipoArticuloId; }
    public void setTipoArticuloId(Long tipoArticuloId) { this.tipoArticuloId = tipoArticuloId; }
    public LocalDateTime getFechaLanzamiento() { return fechaLanzamiento; }
    public void setFechaLanzamiento(LocalDateTime fechaLanzamiento) { this.fechaLanzamiento = fechaLanzamiento; }
    public BigDecimal getValorEstimado() { return valorEstimado; }
    public void setValorEstimado(BigDecimal valorEstimado) { this.valorEstimado = valorEstimado; }
}
