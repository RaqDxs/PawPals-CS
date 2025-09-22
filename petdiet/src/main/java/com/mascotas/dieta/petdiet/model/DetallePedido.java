package com.mascotas.dieta.petdiet.model;
import java.math.BigDecimal;
import jakarta.persistence.*;
import com.mascotas.dieta.petdiet.util.IdGenerator;
@Entity
@Table(name = "DETALLE_PEDIDO")
public class DetallePedido {

    @Id
    @Column(name = "DetCod")
    private Long id;   // BIGINT UNSIGNED

    // Relación con Pedido
    @ManyToOne
    @JoinColumn(name = "DetPedCod", nullable = false)
    private Pedido pedido;

    // Relación con Producto
    @ManyToOne
    @JoinColumn(name = "DetProCod", nullable = false)
    private Producto producto;

    @Column(name = "DetCan", nullable = false)
    private Integer cantidad;

    @Column(name = "DetSub", nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "DetEstReg", length = 1)
    private String estadoRegistro;  // CHAR(1)

    public DetallePedido() {}
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
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
}
