package com.looteria.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
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

}
