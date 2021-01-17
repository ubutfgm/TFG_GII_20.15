package com.tuempresa.formes.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;
/**
 * Entidad Cliente es la clase donde definimos al cliente.
 * @author Antoni Lluis Garcia Exposito
 *
 */
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
	/**
	 * Obtencion de numero de cliente.
	 * @return su numero.
	 */
	public int getNumero() {
		return numero;
	}
	/**
	 * Añadimos el numero de cliente.
	 * @param numero.
	 * 
	 */
	public void setNumero(int numero) {
		this.numero = numero;

	}
	/**
	 * Obtencion de nombre del cliente.
	 * @return su nombre.
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Añadimos el nombre al cliente.
	 * @param nombre.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * En este get nos aseguramos que la direccion no sea nunca null ya que la clase Direccion es 
	 * una clase embeded y si fuese null nos daria error en el codigo.
	 * @return la direccion del cliente.
	 */
	public Direccion getDireccion() {
		if (direccion == null)
			direccion = new Direccion(); // Así nunca es nulo
		return direccion;
	}
	/**
	 * Añadimos la direccion del cliente.
	 * @param direccion.
	 */
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	/**
	 * Obtenemos el telefono del cliente.
	 * @return el telefono.
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * Añadiremos el telefono al cliente.
	 * @param telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * Obtenemos el email del cliente.
	 * @return el email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Añadiremos el email al cliente.
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Obtenemos el cif del cliente.
	 * @return el cif.
	 */
	public String getCIF() {
		return CIF;
	}
	/**
	 * Añadiremos el CIF al cliente.
	 * @param cIF
	 */
	public void setCIF(String cIF) {
		CIF = cIF;
	}

}
