package com.looteria.dto;

public class ReviewDTO {
    private Long idResena;
    private String usuarioRevisor;
    private Integer calificacion;
    private String comentario;
    private String fecha;

    public ReviewDTO() {
    }

    public ReviewDTO(Long idResena, String usuarioRevisor, Integer calificacion, String comentario, String fecha) {
        this.idResena = idResena;
        this.usuarioRevisor = usuarioRevisor;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public Long getIdResena() {
        return idResena;
    }

    public void setIdResena(Long idResena) {
        this.idResena = idResena;
    }

    public String getUsuarioRevisor() {
        return usuarioRevisor;
    }

    public void setUsuarioRevisor(String usuarioRevisor) {
        this.usuarioRevisor = usuarioRevisor;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
