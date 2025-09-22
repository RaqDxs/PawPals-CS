package com.mascotas.dieta.petdiet.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.mascotas.dieta.petdiet.util.IdGenerator;
@Entity
@Table(name = "RECETA")
public class Receta {

    @Id
    @Column(name = "RecCod")
    private Long id;   // BIGINT UNSIGNED

    // Relación con Usuario
    @ManyToOne
    @JoinColumn(name = "RecUsuCod", nullable = false)
    private Usuario usuario;

    @Column(name = "RecArc", nullable = false, columnDefinition = "TEXT")
    private String archivo;  // Ruta o nombre del archivo

    @Column(name = "RecFec", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "RecTip", nullable = false, length = 20)
    private String tipo;   // Ej: PDF, JPG, PNG

    @Column(name = "RecEstReg", length = 1)
    private String estadoRegistro;  // CHAR(1)

    public Receta() {}
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
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
}
