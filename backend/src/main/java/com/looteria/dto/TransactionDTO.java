package com.looteria.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TransactionDTO {
    private Long idTransaccion;
    private Long publicacionId;
    private String productoTitulo;
    private Long compradorId;
    private String compradorNombre;
    private Long vendedorId;
    private String vendedorNombre;
    private String tipo;
    private BigDecimal precioFinal;
    private BigDecimal comision;
    private String estado;
    private LocalDateTime fechaTransaccion;
}
