package com.looteria.dto;

import java.math.BigDecimal;

public class UpdateListingRequestDTO {
    private String descripcionEstado;
    private BigDecimal precio;
    private String tipoTransaccion; 
    private Boolean envio;

    public UpdateListingRequestDTO() {}

    public UpdateListingRequestDTO(String descripcionEstado, BigDecimal precio, 
                                   String tipoTransaccion, Boolean envio) {
        this.descripcionEstado = descripcionEstado;
        this.precio = precio;
        this.tipoTransaccion = tipoTransaccion;
        this.envio = envio;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public Boolean getEnvio() {
        return envio;
    }

    public void setEnvio(Boolean envio) {
        this.envio = envio;
    }
}
