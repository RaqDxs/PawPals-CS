package com.mascotas.dieta.petdiet.model;
import java.math.BigDecimal;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.mascotas.dieta.petdiet.util.IdGenerator;
@Entity
@Table(name = "PEDIDO")
public class Pedido {

    @Id
    @Column(name = "PedCod")
    private Long id;   // BIGINT UNSIGNED

    // Relación con Usuario
    @ManyToOne
    @JoinColumn(name = "PedUsuCod", nullable = false)
    private Usuario usuario;

    @Column(name = "PedFec", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "PedMon", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "PedEst", nullable = false, length = 20)
    private String estado;

    @Column(name = "PedEstReg", length = 1)
    private String estadoRegistro;  // CHAR(1)

    public Pedido() {}
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
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
}
