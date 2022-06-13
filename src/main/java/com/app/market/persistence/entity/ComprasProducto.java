package com.app.market.persistence.entity;

import javax.persistence.*;
import java.util.concurrent.CompletableFuture;

@Entity
@Table(name = "compras_productos")
public class ComprasProducto {

    //internamente contiene la clave primaria ComprasProductoPK
    //Es una PK compuesta, se detalla en una clase adicional y se incluye con @EmbeddedId en la entidad.

    @EmbeddedId
    private ComprasProductoPK id;

    private Integer cantidad;

    private Double total;

    private Boolean estado;

    @ManyToOne
    //De esta manera cuando comprasProducto se vaya a guardar en cascada va a saber
    // a que clave primaria pertenece cada uno de los productos que está en una compra.
    @MapsId("idCompra")
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private Compra compra;


    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;

    public ComprasProductoPK getId() {
        return id;
    }

    public void setId(ComprasProductoPK id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
