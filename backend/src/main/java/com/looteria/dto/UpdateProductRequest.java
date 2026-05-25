package com.looteria.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UpdateProductRequest {
    
    private String titulo;
    private String descripcion;
    private Long plataformaId;
    private Long tipoArticuloId;
    private LocalDateTime fechaLanzamiento;
    private BigDecimal valorEstimado;

}
