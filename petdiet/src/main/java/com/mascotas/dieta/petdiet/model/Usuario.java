package com.mascotas.dieta.petdiet.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import com.mascotas.dieta.petdiet.util.IdGenerator;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @Column(name = "UsuCod")
    private Long id;  // BIGINT UNSIGNED

    @Column(name = "UsuCor", nullable = false, unique = true, length = 80)
    private String correo;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "UsuCon", nullable = false, length = 80)
	private String contrasena;

    @Column(name = "UsuNom", nullable = false, length = 80)
    private String nombre;

    @Column(name = "UsuEstReg", length = 1)
    private String estadoRegistro;

    public Usuario() {}

    @PrePersist
    public void asignarId() {
        if (this.id == null) {
            this.id = IdGenerator.generarId();
        }
    }

    // getters y setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEstadoRegistro() { return estadoRegistro; }
    public void setEstadoRegistro(String estadoRegistro) { this.estadoRegistro = estadoRegistro; }
}
