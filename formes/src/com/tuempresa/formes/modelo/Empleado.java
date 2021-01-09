package com.tuempresa.formes.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
@View(name = "Vendedor", // Esta vista solo se usará cuando se especifique “Simple”
		members = "nombre" // Muestra únicamente el nombre
)
public class Empleado {
	@Embedded // Así para referenciar a una clase incrustable
	private Direccion direccion;
	@Id
	@Column(length = 6)
	private int id;

	@Column(length = 9)
	@Required
	private String nombre;

	@Column
	@Stereotype("EMAIL")
	@Required
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
