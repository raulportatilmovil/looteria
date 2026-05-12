package com.looteria.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacciones")
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaccion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_publicacion", nullable = false)
    private ListingPost publicacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comprador", nullable = false)
    private User comprador;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vendedor", nullable = false)
    private User vendedor;
    
    @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType tipo;
    
    @Column(name = "precio_final", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioFinal;
    
    @Column(name = "fecha_transaccion", nullable = false, updatable = false)
    private LocalDateTime fechaTransaccion;
    
    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus estado = TransactionStatus.PENDIENTE;
    
    @Column(name = "comision", precision = 10, scale = 2)
    private BigDecimal comision;

    public Transaction() {
    }

    public Transaction(Long idTransaccion, ListingPost publicacion, User comprador, User vendedor,
                      TransactionType tipo, BigDecimal precioFinal, LocalDateTime fechaTransaccion,
                      TransactionStatus estado, BigDecimal comision) {
        this.idTransaccion = idTransaccion;
        this.publicacion = publicacion;
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.tipo = tipo;
        this.precioFinal = precioFinal;
        this.fechaTransaccion = fechaTransaccion;
        this.estado = estado;
        this.comision = comision;
    }

    public Long getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Long idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public ListingPost getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(ListingPost publicacion) {
        this.publicacion = publicacion;
    }

    public User getComprador() {
        return comprador;
    }

    public void setComprador(User comprador) {
        this.comprador = comprador;
    }

    public User getVendedor() {
        return vendedor;
    }

    public void setVendedor(User vendedor) {
        this.vendedor = vendedor;
    }

    public TransactionType getTipo() {
        return tipo;
    }

    public void setTipo(TransactionType tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(BigDecimal precioFinal) {
        this.precioFinal = precioFinal;
    }

    public LocalDateTime getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public TransactionStatus getEstado() {
        return estado;
    }

    public void setEstado(TransactionStatus estado) {
        this.estado = estado;
    }

    public BigDecimal getComision() {
        return comision;
    }

    public void setComision(BigDecimal comision) {
        this.comision = comision;
    }
    
    @PrePersist
    protected void onCreate() {
        fechaTransaccion = LocalDateTime.now();
    }
    
    public enum TransactionType {
        VENTA,
        INTERCAMBIO
    }
    
    public enum TransactionStatus {
        PENDIENTE,
        COMPLETADA,
        CANCELADA,
        DEVUELTA
    }
}
