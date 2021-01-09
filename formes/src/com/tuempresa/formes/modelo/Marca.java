package com.tuempresa.formes.modelo;

import javax.persistence.*;
import org.openxava.annotations.*;

@Entity
public class Marca extends Identificable {

	@Column(length = 50)
	@Required
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
