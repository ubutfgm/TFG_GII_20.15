package com.tuempresa.formes.modelo;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
public class Producto {

	@ManyToOne( // La referencia se almacena como una relación en la base de datos
			fetch = FetchType.LAZY, // La referencia se carga bajo demanda
			optional = true) // La referencia puede estar sin valor
	@DescriptionsList // Así la referencia se visualiza usando un combo
	private CategoriaDelArticulo categoria; // Una referencia Java convencional

	public CategoriaDelArticulo getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDelArticulo categoria) {
		this.categoria = categoria;
	}

	@Id
	@Column(length = 9)
	private int numero;

	@Stereotype("DINERO")
	@Required // La propiedad precio se usa para almacenar dinero
	private BigDecimal precioCoste; // Incluye el import java.math.* BigDecimal se suele usar para dinero

	@Stereotype("DINERO")
	@Required // La propiedad precio se usa para almacenar dinero
	private BigDecimal precio; // Incluye el import java.math.* BigDecimal se suele usar para dinero

	@Column(length = 50)
	@Required
	private String descripcion;

	@ManyToOne(fetch = FetchType.LAZY)
	@DescriptionsList
	private Marca marca;

	@Column(length = 9)
	private int cantidadTotal;

	@Column(length = 9)
	private int stockMinimo;

	public String getDescripcion() {
		return descripcion;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Marca getMarca() {
		return marca;

	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public int getCantidadTotal() {
		return cantidadTotal;
	}

	public void setCantidadTotal(int cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	public BigDecimal getPrecioCoste() {
		return precioCoste;
	}

	public void setPrecioCoste(BigDecimal precioCoste) {
		this.precioCoste = precioCoste;
	}

	public int getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

}
