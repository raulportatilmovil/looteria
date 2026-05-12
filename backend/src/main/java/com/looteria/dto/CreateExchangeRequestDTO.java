package com.looteria.dto;

public class CreateExchangeRequestDTO {
    private Long publicacionId;
    private Long solicitanteId;
    private String mensaje;

    public CreateExchangeRequestDTO() {}

    public CreateExchangeRequestDTO(Long publicacionId, Long solicitanteId, String mensaje) {
        this.publicacionId = publicacionId;
        this.solicitanteId = solicitanteId;
        this.mensaje = mensaje;
    }

    public Long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
    }

    public Long getSolicitanteId() {
        return solicitanteId;
    }

    public void setSolicitanteId(Long solicitanteId) {
        this.solicitanteId = solicitanteId;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
