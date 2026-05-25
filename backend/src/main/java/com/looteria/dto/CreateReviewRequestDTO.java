package com.looteria.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateReviewRequestDTO {
    private Long transactionId;
    private Long autorId;
    private Long receptorId;
    private Integer puntuacion;
    private String comentario;

}
