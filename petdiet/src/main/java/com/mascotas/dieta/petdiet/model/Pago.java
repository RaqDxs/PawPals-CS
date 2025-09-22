package com.mascotas.dieta.petdiet.model;
import java.math.BigDecimal;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.mascotas.dieta.petdiet.util.IdGenerator;
@Entity
@Table(name = "PAGO")
public class Pago {

    @Id
    @Column(name = "PagCod")
    private Long id;   // BIGINT UNSIGNED

    // Relación con Pedido
    @ManyToOne
    @JoinColumn(name = "PagPedCod", nullable = false)
    private Pedido pedido;

    @Column(name = "PagMon", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "PagMet", nullable = false, length = 20)
    private String metodo;

    @Column(name = "PagCon", nullable = false, length = 40)
    private String comprobante;

    @Column(name = "PagFec", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "PagEstReg", length = 1)
    private String estadoRegistro;  // CHAR(1)

    public Pago() {}
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
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public String getComprobante() {
		return comprobante;
	}
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
}
