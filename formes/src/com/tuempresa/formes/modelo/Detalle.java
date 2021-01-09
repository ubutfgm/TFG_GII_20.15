package com.tuempresa.formes.modelo;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import com.tuempresa.formes.calculadores.*;

@Embeddable
public class Detalle {
	@Required
	private int cantidad;

	private BigDecimal precio;

	@Required
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Producto producto;

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

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;

	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(Producto precio) {
		this.precio = precio.getPrecio();
	}

	@Stereotype("DINERO")
	@Depends("producto.numero, cantidad")
	public BigDecimal getImporte() {

		return new BigDecimal(cantidad).multiply(producto.getPrecio());
	}

}