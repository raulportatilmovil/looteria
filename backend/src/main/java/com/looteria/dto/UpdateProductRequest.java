package com.looteria.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductRequest {
    
    private String titulo;
    
    private String descripcion;
    
    private Long plataformaId;
    
    private Long tipoArticuloId;
    
    private Long franquiciaId;
    
    private LocalDateTime fechaLanzamiento;
    
    private BigDecimal valorEstimado;
}
