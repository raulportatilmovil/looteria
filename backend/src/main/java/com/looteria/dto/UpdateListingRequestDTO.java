package com.looteria.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class UpdateListingRequestDTO {
    private String descripcionEstado;
    private BigDecimal precio;
    private String tipoTransaccion;

}
