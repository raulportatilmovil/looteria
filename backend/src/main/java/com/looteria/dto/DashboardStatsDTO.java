package com.looteria.dto;

public class DashboardStatsDTO {
    private long totalUsuarios;
    private long usuariosVerificados;
    private long publicacionesActivas;
    private long transaccionesCompletadas;

    public DashboardStatsDTO() {}

    public DashboardStatsDTO(long totalUsuarios, long usuariosVerificados, long publicacionesActivas, long transaccionesCompletadas) {
        this.totalUsuarios = totalUsuarios;
        this.usuariosVerificados = usuariosVerificados;
        this.publicacionesActivas = publicacionesActivas;
        this.transaccionesCompletadas = transaccionesCompletadas;
    }

    public long getTotalUsuarios() {
        return totalUsuarios;
    }

    public void setTotalUsuarios(long totalUsuarios) {
        this.totalUsuarios = totalUsuarios;
    }

    public long getUsuariosVerificados() {
        return usuariosVerificados;
    }

    public void setUsuariosVerificados(long usuariosVerificados) {
        this.usuariosVerificados = usuariosVerificados;
    }

    public long getPublicacionesActivas() {
        return publicacionesActivas;
    }

    public void setPublicacionesActivas(long publicacionesActivas) {
        this.publicacionesActivas = publicacionesActivas;
    }

    public long getTransaccionesCompletadas() {
        return transaccionesCompletadas;
    }

    public void setTransaccionesCompletadas(long transaccionesCompletadas) {
        this.transaccionesCompletadas = transaccionesCompletadas;
    }
}
