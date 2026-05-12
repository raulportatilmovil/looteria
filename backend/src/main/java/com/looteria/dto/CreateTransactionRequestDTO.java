package com.looteria.dto;

import java.math.BigDecimal;

public class CreateTransactionRequestDTO {
    private Long publicacionId;
    private Long compradorId;
    private Long vendedorId;
    private String tipo; // VENTA, INTERCAMBIO
    private BigDecimal precioFinal;

    public CreateTransactionRequestDTO() {}

    public CreateTransactionRequestDTO(Long publicacionId, Long compradorId, Long vendedorId,
                                      String tipo, BigDecimal precioFinal) {
        this.publicacionId = publicacionId;
        this.compradorId = compradorId;
        this.vendedorId = vendedorId;
        this.tipo = tipo;
        this.precioFinal = precioFinal;
    }

    public Long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
    }

    public Long getCompradorId() {
        return compradorId;
    }

    public void setCompradorId(Long compradorId) {
        this.compradorId = compradorId;
    }

    public Long getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(Long vendedorId) {
        this.vendedorId = vendedorId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(BigDecimal precioFinal) {
        this.precioFinal = precioFinal;
    }
}
