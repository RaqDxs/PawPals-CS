package com.mascotas.dieta.petdiet.model;

import jakarta.persistence.*;
import com.mascotas.dieta.petdiet.util.IdGenerator;
@Entity
@Table(name = "PLAN_DIETA")
public class PlanDieta {

    @Id
    @Column(name = "PlaCod")
    private Long id;   // BIGINT UNSIGNED

    @Column(name = "PlaNom", nullable = false, unique = true, length = 80)
    private String nombre;

    @Column(name = "PlaIng", nullable = false, columnDefinition = "TEXT")
    private String ingredientes;

    @Column(name = "PlaFot", columnDefinition = "TEXT")
    private String foto;

    @Column(name = "PlaDes", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "PlaEti", columnDefinition = "TEXT")
    private String etiquetas;

    @Column(name = "PlaEstReg", length = 1)
    private String estadoRegistro;  // CHAR(1)

    public PlanDieta() {}
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEtiquetas() {
		return etiquetas;
	}
	public void setEtiquetas(String etiquetas) {
		this.etiquetas = etiquetas;
	}
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
}
