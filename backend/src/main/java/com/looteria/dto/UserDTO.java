package com.looteria.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long idUsuario;
    private String email;
    private String nombreUsuario;
    private String rol;
    private LocalDateTime fechaRegistro;
    private String ubicacion;
    private Long puntosAcumulados;
    private Boolean verificadoIdentidad;
    private BigDecimal reputacionMedia;

}
