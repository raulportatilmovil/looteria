package com.looteria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long idResena;
    private String usuarioRevisor;
    private Integer calificacion;
    private String comentario;
    private String fecha;

}
