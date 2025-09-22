package com.mascotas.dieta.petdiet.model;
import java.math.BigDecimal;
import jakarta.persistence.*;
import com.mascotas.dieta.petdiet.util.IdGenerator;
@Entity
@Table(name = "PRODUCTO")
public class Producto {

    @Id
    @Column(name = "ProCod")
    private Long id;   // BIGINT UNSIGNED

    @Column(name = "ProNom", nullable = false, unique = true, length = 80)
    private String nombre;

    @Column(name = "ProDes", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "ProCat", nullable = false, length = 40)
    private String categoria;

    @Column(name = "ProPre", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "ProFot", columnDefinition = "TEXT")
    private String foto;

    @Column(name = "ProEti", columnDefinition = "TEXT")
    private String etiquetas;

    @Column(name = "ProEstReg", length = 1)
    private String estadoRegistro;  // CHAR(1)

    public Producto() {}
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
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
