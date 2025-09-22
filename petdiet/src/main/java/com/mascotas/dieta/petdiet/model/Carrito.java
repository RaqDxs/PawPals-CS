package com.mascotas.dieta.petdiet.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.mascotas.dieta.petdiet.util.IdGenerator;
@Entity
@Table(name = "CARRITO")
public class Carrito {

    @Id
    @Column(name = "CarCod")
    private Long id;   // BIGINT UNSIGNED

    // Relación con Usuario
    @ManyToOne
    @JoinColumn(name = "CarUsuCod", nullable = false)
    private Usuario usuario;

    @Column(name = "CarFec", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "CarEstReg", length = 1)
    private String estadoRegistro;  // CHAR(1)

    public Carrito() {}
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
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
}
