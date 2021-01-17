package com.tuempresa.formes.modelo;

import javax.persistence.*;
import org.openxava.annotations.*;
/**
 * Entidad que sirve para ir añadiendo las diferentes marcas con las que trabajara el negocio.
 * @author Antoni Lluis Garcia Exposito
 *
 */
@Entity
public class Marca extends Identificable {

	@Column(length = 50)
	@Required
	private String nombre;
	/**
	 * 
	 * @return el nombre de la marca.
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Añadimos el nombre de la nueva marca.
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
