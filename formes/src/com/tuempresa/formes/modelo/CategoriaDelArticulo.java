package com.tuempresa.formes.modelo;

import javax.persistence.*;
import javax.persistence.Entity;
import org.openxava.annotations.*;

/**
 * Entidad que nos servira para añadir categorias a los articulos (Tipo de
 * articulo que es). Poniendo este nombre nos aseguramos que la aplicacion no
 * solo es para una tienda de ropa.
 * 
 * @author Antoni Lluis Garcia Exposito
 *
 */
@Entity
public class CategoriaDelArticulo extends Identificable {

	@Required
	@Column(length = 50)
	private String nombre;

	/**
	 * Get para obtener el nombre de la categoria del producto.
	 * 
	 * @return el nombre de la categoria.
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Añadiremos el nombre de la categoria del producto.
	 * @param nombre.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
