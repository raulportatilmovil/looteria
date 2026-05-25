package com.looteria.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
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
    private String imagenUrl;

}
