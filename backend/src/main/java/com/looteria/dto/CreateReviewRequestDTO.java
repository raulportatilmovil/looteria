package com.looteria.dto;

public class CreateReviewRequestDTO {
    private Long transactionId;
    private Long autorId;
    private Long receptorId;
    private Integer puntuacion;
    private String comentario;

    public CreateReviewRequestDTO() {
    }

    public CreateReviewRequestDTO(Long transactionId, Long autorId, Long receptorId,
                                   Integer puntuacion, String comentario) {
        this.transactionId = transactionId;
        this.autorId = autorId;
        this.receptorId = receptorId;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public Long getReceptorId() {
        return receptorId;
    }

    public void setReceptorId(Long receptorId) {
        this.receptorId = receptorId;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
