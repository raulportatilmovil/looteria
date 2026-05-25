package com.looteria.dto;

public interface ListingSearchProjection {
    Long getIdPublicacion();
    String getTitulo();
    String getDescripcion();
    String getPlataformaNombre();
    String getTipoArticuloNombre();
    java.math.BigDecimal getPrecio();
    String getEstadoArticuloNombre();
    String getTipoTransaccion();
    String getRegionNombre();
    String getNombreUsuario();
    String getDescripcionEstado();
    String getIdiomaNombre();
    java.time.LocalDateTime getFechaCreacion();
}
