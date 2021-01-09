package com.tuempresa.formes.modelo;

import javax.persistence.*;
import javax.persistence.Entity;
import org.openxava.annotations.*;

@Entity
public class CategoriaDelArticulo extends Identificable {

	@Required
	@Column(length = 50)
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
