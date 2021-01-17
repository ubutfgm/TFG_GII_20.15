package com.tuempresa.formes.modelo;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;
/**
 * Estamos enfrente de la entidad más importante a nivel de clase, ya que casi todo el proyecto se mueve entorno a productos.
 * @author Antoni Lluis Garcia Exposito
 *
 */
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
	/**
	 * 
	 * @return La descripcion del producto, es decir el nombre.
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * 
	 * @return el numero de producto que es.
	 */
	public int getNumero() {
		return numero;
	}
	/**
	 * Definimos el numero del producto.
	 * @param numero
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	/**
	 * 
	 * @return la marca del producto.
	 */
	public Marca getMarca() {
		return marca;

	}
	/**
	 * Añadimos la marca al producto.
	 * @param marca
	 */
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	/**
	 * Añadimos el nombre del producto. 
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * 
	 * @return el precio del producto.
	 */
	public BigDecimal getPrecio() {
		return precio;
	}
	/**
	 * Añadimos el precio del producto.
	 * @param precio
	 */
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	/**
	 * 
	 * @return la cantidad del producto que tenemos.
	 */
	public int getCantidadTotal() {
		return cantidadTotal;
	}
	/**
	 * Definimos la cantidad que tenemos.
	 * @param cantidadTotal
	 */
	public void setCantidadTotal(int cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}
	/**
	 * 
	 * @return El precio de coste del producto.
	 */
	public BigDecimal getPrecioCoste() {
		return precioCoste;
	}
	/**
	 * Definimos el precio de coste.
	 * @param precioCoste
	 */
	public void setPrecioCoste(BigDecimal precioCoste) {
		this.precioCoste = precioCoste;
	}
	/**
	 * 
	 * @return el punto pedido.
	 */
	public int getStockMinimo() {
		return stockMinimo;
	}
	/**
	 * Definimos el punto pedido para gestionar los pedidos.
	 * @param stockMinimo
	 */
	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

}
