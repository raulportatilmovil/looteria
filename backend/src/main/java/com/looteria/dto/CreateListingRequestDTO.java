package com.looteria.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CreateListingRequestDTO {
    private Long userId;
    private String producto;
    private String descripcion;
    private String plataforma;
    private String tipoTransaccion;
    private BigDecimal precio;
    private String estado;
    private String descripcionEstado;
    private String especificaciones;
    private String idioma;
    private String region;

}
