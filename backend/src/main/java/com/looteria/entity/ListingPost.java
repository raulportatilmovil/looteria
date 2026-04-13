package com.looteria.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "publicaciones")
public class ListingPost {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPublicacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private User usuario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private Product producto;
    
    @Column(name = "tipo_transaccion", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType tipoTransaccion;
    
    @Column(name = "precio", precision = 10, scale = 2)
    private BigDecimal precio;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_articulo_id")
    private Category estadoArticulo;
    
    @Column(name = "descripcion_estado", columnDefinition = "TEXT")
    private String descripcionEstado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idioma_id")
    private Category idioma;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Category region;
    
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    
    @Column(name = "estado_publicacion", nullable = false)
    @Enumerated(EnumType.STRING)
    private PublicationStatus estadoPublicacion = PublicationStatus.ACTIVA;
    
    @Column(name = "envio", nullable = false)
    private Boolean envio = false;

    // Constructores
    public ListingPost() {
    }

    public ListingPost(Long idPublicacion, User usuario, Product producto, TransactionType tipoTransaccion,
                      BigDecimal precio, Category estadoArticulo, String descripcionEstado, Category idioma,
                      Category region, LocalDateTime fechaCreacion, PublicationStatus estadoPublicacion, Boolean envio) {
        this.idPublicacion = idPublicacion;
        this.usuario = usuario;
        this.producto = producto;
        this.tipoTransaccion = tipoTransaccion;
        this.precio = precio;
        this.estadoArticulo = estadoArticulo;
        this.descripcionEstado = descripcionEstado;
        this.idioma = idioma;
        this.region = region;
        this.fechaCreacion = fechaCreacion;
        this.estadoPublicacion = estadoPublicacion;
        this.envio = envio;
    }

    // Getters y Setters
    public Long getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Long idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Product getProducto() {
        return producto;
    }

    public void setProducto(Product producto) {
        this.producto = producto;
    }

    public TransactionType getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TransactionType tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Category getEstadoArticulo() {
        return estadoArticulo;
    }

    public void setEstadoArticulo(Category estadoArticulo) {
        this.estadoArticulo = estadoArticulo;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    public Category getIdioma() {
        return idioma;
    }

    public void setIdioma(Category idioma) {
        this.idioma = idioma;
    }

    public Category getRegion() {
        return region;
    }

    public void setRegion(Category region) {
        this.region = region;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public PublicationStatus getEstadoPublicacion() {
        return estadoPublicacion;
    }

    public void setEstadoPublicacion(PublicationStatus estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
    }

    public Boolean getEnvio() {
        return envio;
    }

    public void setEnvio(Boolean envio) {
        this.envio = envio;
    }

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }
    
    public enum TransactionType {
        VENTA,
        INTERCAMBIO
    }
    
    public enum PublicationStatus {
        ACTIVA,
        VENDIDA,
        CANCELADA,
        PAUSADA
    }
}
