
package com.tuempresa.formes.modelo;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import com.tuempresa.formes.calculadores.*;
/**
 * Es la clase que se encara de las colecciones de productos en las entidades de Pedido y tpv.
 * Es tambien la entidad encargada de 
 * @author Antoni Lluis Garcia Exposito
 *
 */
@Embeddable
public class ProductoPara {

	@Required
	private int cantidad;

	@Required
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Producto producto;

	private BigDecimal precioCoste;
	private BigDecimal precioAux;
	private String nombreProducto;
	@DefaultValueCalculator(value = CalculadorPrecioCosteUnidad.class, // Esta clase calcula el valor inicial
			properties = @PropertyValue(name = "numeroProducto", // La propiedad numeroProducto del calculador...
					from = "producto.numero") // ... se llena con el valor de producto.numero de la entidad
	)
	@Stereotype("DINERO")
	private BigDecimal precioCostePorUnidad;
	/**
	 * 
	 * @return el precio de coste por unidad, en caso de ser nulñl lo igualamos a 0.
	 */
	public BigDecimal getPrecioCostePorUnidad() {
		return precioCostePorUnidad == null ? BigDecimal.ZERO : precioCostePorUnidad;
	}
	/**
	 * Definimos el precio de coste por unidad.
	 * @param precioCostePorUnidad
	 */
	public void setPrecioCostePorUnidad(BigDecimal precioCostePorUnidad) {
		this.precioCostePorUnidad = precioCostePorUnidad;
	}

	@DefaultValueCalculator(value = CalculadorPrecioPorUnidad.class, // Esta clase calcula el valor inicial
			properties = @PropertyValue(name = "numeroProducto", // La propiedad numeroProducto del calculador...
					from = "producto.numero") // ... se llena con el valor de producto.numero de la entidad
	)
	@Stereotype("DINERO")
	private BigDecimal precioPorUnidad; // Una propiedad persistente convencional...
	/**
	 * 
	 * @return el precio por unidad.
	 */
	public BigDecimal getPrecioPorUnidad() { //
		return precioPorUnidad == null ? BigDecimal.ZERO : precioPorUnidad; // Así nunca devuelve nulo
	}
	/**
	 * Definimos el precio por unidad.
	 * @param precioPorUnidad
	 */
	public void setPrecioPorUnidad(BigDecimal precioPorUnidad) {
		this.precioPorUnidad = precioPorUnidad;
	}
	/**
	 * 
	 * @return la cantidad de cada producto.
	 */
	public int getCantidad() {
		return cantidad;
	}
	/**
	 * Definimos la cantidad.
	 * @param cantidad
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * 
	 * @return el precio del producto.
	 */
	public BigDecimal getPrecioAux() {
		precioAux = producto.getPrecio();
		return precioAux;
	}
	/**
	 * Definimos el precio del producto.
	 * @param precioAux
	 */
	public void setPrecioAux(BigDecimal precioAux) {
		this.precioAux = precioAux;
	}
	/**
	 * 
	 * @return el producto.
	 */
	public Producto getProducto() {
		return producto;
	}
	/**
	 * Añadimos el producto.
	 * @param producto
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	/**
	 * 
	 * @return el nombre del producto.
	 */
	public String getNombreProducto() {
		nombreProducto = this.producto.getDescripcion();
		return nombreProducto;
	}	
	/**
	 * Añadimos el nombre del producto.
	 * @param nombreProducto
	 */
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	/**
	 * 
	 * @return el precio de coste.
	 */
	public BigDecimal getPrecioCoste() {
		return precioCoste;
	}

	public void setPrecioCoste(Producto precioCoste) {
		this.precioCoste = precioCoste.getPrecioCoste();
	}
/**
 * 
 * @return el producto dse la cantidad por el precio de cada unidad.
 */
	@Stereotype("DINERO")
	@Depends("producto.numero, cantidad")
	public BigDecimal getImporte() {
		return new BigDecimal(cantidad).multiply(this.getPrecioPorUnidad());
	}
	/**
	 * 
	 * @return el producto dse la cantidad por el precio de coste de cada unidad.
	 */
	@Stereotype("DINERO")
	@Depends("producto.numero, cantidad")
	public BigDecimal getImporte2() {

		return new BigDecimal(cantidad).multiply(this.getPrecioCostePorUnidad());
	}

}