package com.tuempresa.formes.modelo;

import java.math.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
/**
 * Entidad para la creación de facturas pendientes que tendra el negocio.
 * @author Antoni Lluis Garcia Exposito
 *
 */
@Entity
public class facturasPendientes extends Identificable {

	@Required
	@Column(length = 50)
	private String numeroFactura;

	@Required
	@ManyToOne( // La referencia se almacena como una relación en la base de datos
			fetch = FetchType.LAZY, // La referencia se carga bajo demanda
			optional = true) // La referencia puede estar sin valor
	@DescriptionsList // Así la referencia se visualiza usando un combo
	private Marca marca; // Una referencia Java convencional

	@Required
	@Stereotype("FECHAHORA")
	private Date fechaVencimiento;

	@Required
	@Stereotype("DINERO")
	private BigDecimal cantidad;

	@Required
	@Column(length = 70)
	private String iban;
	/**
	 * 
	 * @return el string que equivale al numero de factura.
	 */
	public String getNumeroFactura() {
		return numeroFactura;
	}
	/**
	 *Añadiremos el numero de factura.
	 *@param numeroFactura
	 */
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	/**
	 * 
	 * @return nos devuelve el iban de la factura pendiente.
	 */
	public String getIban() {
		return iban;
	}
	/**
	 * Añadimos el iban a la factura.
	 * @param iban
	 */
	public void setIban(String iban) {
		this.iban = iban;
	}
	/**
	 * 
	 * @return la marca a quien le debemos la factura.
	 */
	public Marca getMarca() {
		return marca;
	}
	/**
	 * Añadimos a que marca le debemos la factura.
	 * @param marca
	 */
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	/**
	 * 
	 * @return la fecha de vencimiento de la factura.
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	/**
	 * Añadiremos la fecha en la que vencera la factura.
	 * @param fechaVencimiento
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	/**
	 * 
	 * @return el importe que se debe.
	 */
	public BigDecimal getCantidad() {
		return cantidad;
	}
	/**
	 * Añadiremos la camtidad que se deberá.
	 * @param cantidad
	 */
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

}
