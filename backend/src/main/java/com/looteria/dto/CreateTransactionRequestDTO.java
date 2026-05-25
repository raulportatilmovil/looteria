package com.looteria.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CreateTransactionRequestDTO {
    private Long publicacionId;
    private Long compradorId;
    private Long vendedorId;
    private String tipo; // VENTA, INTERCAMBIO
    private BigDecimal precioFinal;

}
