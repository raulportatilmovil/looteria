package com.looteria.dto;

public interface ListingSearchProjection {
    Long getIdPublicacion();
    Long getIdProducto();
    String getTitulo();
    String getDescripcion();
    String getPlataformaNombre();
    String getTipoArticuloNombre();
    String getFranquiciaNombre();
    java.math.BigDecimal getPrecio();
    String getEstadoArticuloNombre();
    String getTipoTransaccion();
    String getRegionNombre();
    String getNombreUsuario();
    String getDescripcionEstado();
    String getIdiomaNombre();
    java.time.LocalDateTime getFechaCreacion();
}
