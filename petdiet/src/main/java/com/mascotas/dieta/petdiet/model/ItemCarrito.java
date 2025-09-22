package com.mascotas.dieta.petdiet.model;

import jakarta.persistence.*;
import com.mascotas.dieta.petdiet.util.IdGenerator;
@Entity
@Table(name = "ITEM_CARRITO")
public class ItemCarrito {

    @Id
    @Column(name = "IteCod")
    private Long id;   // BIGINT UNSIGNED

    // Relación con Carrito
    @ManyToOne
    @JoinColumn(name = "IteCarCod", nullable = false)
    private Carrito carrito;

    // Relación con Producto
    @ManyToOne
    @JoinColumn(name = "IteProCod", nullable = false)
    private Producto producto;

    @Column(name = "IteCan", nullable = false)
    private Integer cantidad;

    @Column(name = "IteEstReg", length = 1)
    private String estadoRegistro;  // CHAR(1)

    public ItemCarrito() {}
    @PrePersist
    public void asignarId() {
        if (this.id == null) {
            this.id = IdGenerator.generarId();
        }
    }
    // getters y setters…
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Carrito getCarrito() {
		return carrito;
	}
	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
}
