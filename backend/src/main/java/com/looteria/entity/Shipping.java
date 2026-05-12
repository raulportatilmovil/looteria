package com.looteria.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "envios")
public class Shipping {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnvio;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transaccion", nullable = false)
    private Transaction transaccion;
    
    @Column(name = "proveedor", nullable = false)
    private String proveedor;
    
    @Column(name = "numero_seguimiento", unique = true)
    private String numeroSeguimiento;
    
    @Column(name = "detalles_seguimiento", columnDefinition = "TEXT")
    private String detallesSeguimiento;
    
    @Column(name = "coste", precision = 10, scale = 2)
    private BigDecimal coste;
    
    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private ShippingStatus estado = ShippingStatus.PREPARANDO;
    
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    public Shipping() {
    }

    public Shipping(Long idEnvio, Transaction transaccion, String proveedor, String numeroSeguimiento,
                    String detallesSeguimiento, BigDecimal coste, ShippingStatus estado, LocalDateTime fechaCreacion) {
        this.idEnvio = idEnvio;
        this.transaccion = transaccion;
        this.proveedor = proveedor;
        this.numeroSeguimiento = numeroSeguimiento;
        this.detallesSeguimiento = detallesSeguimiento;
        this.coste = coste;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Long idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Transaction getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaction transaccion) {
        this.transaccion = transaccion;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getNumeroSeguimiento() {
        return numeroSeguimiento;
    }

    public void setNumeroSeguimiento(String numeroSeguimiento) {
        this.numeroSeguimiento = numeroSeguimiento;
    }

    public String getDetallesSeguimiento() {
        return detallesSeguimiento;
    }

    public void setDetallesSeguimiento(String detallesSeguimiento) {
        this.detallesSeguimiento = detallesSeguimiento;
    }

    public BigDecimal getCoste() {
        return coste;
    }

    public void setCoste(BigDecimal coste) {
        this.coste = coste;
    }

    public ShippingStatus getEstado() {
        return estado;
    }

    public void setEstado(ShippingStatus estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }
    
    public enum ShippingStatus {
        PREPARANDO,
        ENVIADO,
        EN_TRANSITO,
        ENTREGADO,
        DEVUELTO
    }
}
