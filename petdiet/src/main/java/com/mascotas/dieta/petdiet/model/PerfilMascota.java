package com.mascotas.dieta.petdiet.model;
import java.math.BigDecimal;
import jakarta.persistence.*;
import com.mascotas.dieta.petdiet.util.IdGenerator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name = "PERFIL_MASCOTA")
public class PerfilMascota {

    @Id
    @Column(name = "PerCod")
	@JsonSerialize(using = ToStringSerializer.class)
    private Long id;   // BIGINT UNSIGNED

    // Relación con Usuario
    @ManyToOne
    @JoinColumn(name = "PerUsuCod", nullable = false)
    private Usuario usuario;

    @Column(name = "PerNom", nullable = false, length = 80)
    private String nombre;

    @Column(name = "PerEsp", nullable = false, length = 20)
    private String especie;

    @Column(name = "PerRaz", nullable = false, length = 40)
    private String raza;

    @Column(name = "PerEda", nullable = false)
    private Integer edad;

    @Column(name = "PerPes", nullable = false, precision = 10, scale = 2)
    private BigDecimal peso;

    @Column(name = "PerFot", columnDefinition = "TEXT")
    private String foto;

    @Column(name = "PerAle", columnDefinition = "TEXT")
    private String alergias;

    @Column(name = "PerCon", columnDefinition = "TEXT")
    private String condiciones;

    @Column(name = "PerEstReg", length = 1)
    private String estadoRegistro;  // CHAR(1)

    public PerfilMascota() {}
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public BigDecimal getPeso() {
		return peso;
	}
	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getAlergias() {
		return alergias;
	}
	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}
	public String getCondiciones() {
		return condiciones;
	}
	public void setCondiciones(String condiciones) {
		this.condiciones = condiciones;
	}
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
}
