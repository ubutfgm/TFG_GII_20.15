package com.tuempresa.formes.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
@View(name = "Simple", // Esta vista solo se usará cuando se especifique “Simple”
		members = "numero, nombre, email, CIF" // Muestra únicamente numero y nombre en la misma línea
)
public class Cliente {
	@Embedded // Así para referenciar a una clase incrustable
	private Direccion direccion; // Una referencia Java convencional

	@Id
	@Column(length = 6)
	private int numero;

	@Column
	@Required
	private String telefono;

	@Column
	@Required
	private String nombre;

	@Column
	@Required
	private String CIF;

	@Column
	@Stereotype("EMAIL")
	@Required
	private String email;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Direccion getDireccion() {
		if (direccion == null)
			direccion = new Direccion(); // Así nunca es nulo
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCIF() {
		return CIF;
	}

	public void setCIF(String cIF) {
		CIF = cIF;
	}

}
