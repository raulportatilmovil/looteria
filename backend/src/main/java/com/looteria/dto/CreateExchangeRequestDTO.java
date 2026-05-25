package com.looteria.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateExchangeRequestDTO {
    private Long publicacionId;
    private Long solicitanteId;
    private String mensaje;

}
