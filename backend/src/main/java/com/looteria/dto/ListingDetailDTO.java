package com.looteria.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ListingDetailDTO {

    // Publicación
    private Long idPublicacion;
    private String tipoTransaccion;
    private BigDecimal precio;
    private String descripcionEstado;
    private LocalDateTime fechaCreacion;
    private String estadoPublicacion;
    private Boolean destacado;

    // Categorías
    private String estadoArticulo;
    private String estado;          
    private String idioma;
    private String region;

    // Producto
    private String titulo;
    private String producto;        
    private String descripcion;
    private String especificaciones; 
    private String plataforma;
    private String tipoArticulo;
    private LocalDateTime fechaLanzamiento;

    // Usuario
    private Long idUsuario;
    private Long usuarioId;         
    private String nombreUsuario;
    private String usuarioNombre;  
    private String email;
    private String usuarioEmail;     
    private String usuarioUbicacion;
    private BigDecimal usuarioReputacion;
    private Long usuarioVentas;

    // Imágenes
    private List<String> imagenes;

    // Para reseñas
    private Long transaccionId;

}