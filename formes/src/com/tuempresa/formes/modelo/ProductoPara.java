
package com.tuempresa.formes.modelo;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import com.tuempresa.formes.calculadores.*;

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

	public BigDecimal getPrecioCostePorUnidad() {
		return precioCostePorUnidad == null ? BigDecimal.ZERO : precioCostePorUnidad;
	}

	public void setPrecioCostePorUnidad(BigDecimal precioCostePorUnidad) {
		this.precioCostePorUnidad = precioCostePorUnidad;
	}

	@DefaultValueCalculator(value = CalculadorPrecioPorUnidad.class, // Esta clase calcula el valor inicial
			properties = @PropertyValue(name = "numeroProducto", // La propiedad numeroProducto del calculador...
					from = "producto.numero") // ... se llena con el valor de producto.numero de la entidad
	)
	@Stereotype("DINERO")
	private BigDecimal precioPorUnidad; // Una propiedad persistente convencional...

	public BigDecimal getPrecioPorUnidad() { //
		return precioPorUnidad == null ? BigDecimal.ZERO : precioPorUnidad; // Así nunca devuelve nulo
	}

	public void setPrecioPorUnidad(BigDecimal precioPorUnidad) {
		this.precioPorUnidad = precioPorUnidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecioAux() {
		precioAux = producto.getPrecio();
		System.out.println(precioAux);
		return precioAux;
	}

	public void setPrecioAux(BigDecimal precioAux) {
		this.precioAux = precioAux;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getNombreProducto() {
		nombreProducto = this.producto.getDescripcion();
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public BigDecimal getPrecioCoste() {
		return precioCoste;
	}

	public void setPrecioCoste(Producto precioCoste) {
		this.precioCoste = precioCoste.getPrecioCoste();
	}

	@Stereotype("DINERO")
	@Depends("producto.numero, cantidad")
	public BigDecimal getImporte() {
		// BigDecimal prova = new BigDecimal(cantidad).multiply(producto.getPrecio());
		// System.out.println(prova);
		System.out.println("cantidad = " + cantidad + " Precio= " + this.getPrecioPorUnidad());
		return new BigDecimal(cantidad).multiply(this.getPrecioPorUnidad());
	}

	@Stereotype("DINERO")
	@Depends("producto.numero, cantidad")
	public BigDecimal getImporte2() {

		return new BigDecimal(cantidad).multiply(this.getPrecioCostePorUnidad());
	}

}