package com.tuempresa.formes.modelo;

import javax.ejb.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.*;

import org.openxava.annotations.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;
/**
 * Entidad donde el usuario se va a pasar más tiempo ya que es la que se encarga de las ventas.
 * @author Antoni Lluis Garcia Exposito
 *
 */
@Entity
@View(members = // Esta vista no tiene nombre, por tanto será la vista usada por defecto
"numero , fecha, empleado ;" + // Separados por coma significa en la misma línea
		"cliente;" + // Punto y coma significa nueva línea
		"productoPara;" + "observaciones")
public class tpv extends Identificable {

	@Column(length = 6)
	private int numero;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@ReferenceView("Vendedor")
	private Empleado empleado;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@ReferenceView("Simple") // El cliente es obligatorio
	private Cliente cliente;

	@Required
	@DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
	private LocalDate fecha;

	@Stereotype("MEMO")
	private String observaciones;

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	@ElementCollection
	@ListProperties("producto.numero, producto.descripcion, producto.cantidadTotal, cantidad, precioPorUnidad, "
			+ "importe+[" +

			"tpv.porcentajeIVA," + "tpv.iva," + "tpv.importeTotal," + "tpv.pagaCon, tpv.resto" +

			"]")
	private Collection<ProductoPara> productoPara;

	@Digits(integer = 2, fraction = 0) // Para indicar su tamaño
	private BigDecimal porcentajeIVA;

	@ReadOnly
	@Stereotype("DINERO")
	@Calculation("sum(productoPara.importe) * porcentajeIVA / 100")
	private BigDecimal iva;

	@ReadOnly
	@Stereotype("DINERO")
	@Calculation("sum(productoPara.importe)+iva")
	private BigDecimal importeTotal;

	@Stereotype("DINERO")
	private BigDecimal pagaCon;

	@ReadOnly
	@Stereotype("DINERO")
	@Calculation("(pagaCon-importeTotal)")
	private BigDecimal resto;
/**
 * 
 * @return la colleccion de productos que se van a vender.
 */
	public Collection<ProductoPara> getProductoPara() {
		return productoPara;
	}
/**
 * Definimos la colección de productos que se va a vendere.
 * @param productoPara
 */
	public void setProductoPara(Collection<ProductoPara> productoPara) {
		this.productoPara = productoPara;
	}
/**
 * 
 * @return el numero de venta.
 */
	public int getNumero() {
		return numero;
	}
/**
 * Definimos que numero de venta es.
 * @param numero
 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
/**
 * 
 * @return la fecha en que se ha hecho la venta.
 */
	public LocalDate getFecha() {
		return fecha;
	}
	/**
	 * Definimos en que fecha se ha hecho la venta.
	 * @param fecha
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	/**
	 * 
	 * @return las posibles observaciones que tendra la venta.
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * Añadimos las observaciones pertinientes.
	 * @param observaciones
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * 
	 * @return el cliente de la venta.
	 */
	public Cliente getCliente() {
		return cliente;
	}
	/**
	 * Añadimos que cliente nos ha hecho la compra.
	 * @param cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	/**
	 * 
	 * @return el procentaje del IVA.
	 */
	public BigDecimal getPorcentajeIVA() {
		return porcentajeIVA;
	}
	/**
	 * Definimos el porcentaje del IVA.
	 * @param porcentajeIVA
	 */
	public void setPorcentajeIVA(BigDecimal porcentajeIVA) {
		this.porcentajeIVA = porcentajeIVA;
	}
	/**
	 * 
	 * @return El iva calculado.
	 */
	public BigDecimal getIva() {
		return iva;
	}
	/**
	 * Definimos el iva.
	 * @param iva
	 */
	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}
	/**
	 * 
	 * @return nos devuelve el precio total de la venta, iva incluido.
	 */
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}	
	/**
	 * Definimos el precio total.
	 * @param importeTotal
	 */
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}
	/**
	 * 
	 * @return la cantidad con la que paga el cliente.
	 */
	public BigDecimal getPagaCon() {
		return pagaCon;
	}
	/**
	 * Definimos el dinero con el que paga el cliente.
	 * @param pagaCon
	 */
	public void setPagaCon(BigDecimal pagaCon) {
		this.pagaCon = pagaCon;
	}
	/**
	 * 
	 * @return el cambio que se le tiene que devolver al cliente.
	 */
	public BigDecimal getResto() {
		return resto;
	}
	/**
	 * Definimos el resto.
	 * @param resto
	 */
	public void setResto(BigDecimal resto) {
		this.resto = resto;
	}
	/**
	 * En caso de que el stock del producto añadido este por debajo del punto pedido, este metodo lo añadira al pedido abierto.
	 */
	public void anadirApedido() {
		productoPara = this.getProductoPara();
		Pedido pedido;

		try {
			pedido = Pedido.findActive();
		} catch (ObjectNotFoundException e) {
			pedido = new Pedido();

		}
		Producto producto;

		ProductoPara productoParaPedido;

		for (ProductoPara lineaProductoPara : productoPara) {

			producto = XPersistence.getManager().find(Producto.class, lineaProductoPara.getProducto().getNumero());

			if (producto.getCantidadTotal() < producto.getStockMinimo()) {

				productoParaPedido = new ProductoPara();
				productoParaPedido.setProducto(producto);
				productoParaPedido.setCantidad(producto.getStockMinimo() - producto.getCantidadTotal());
				productoParaPedido.setPrecioCostePorUnidad(producto.getPrecioCoste());
				pedido.getProductoPara().add(productoParaPedido);

			}

		}
		XPersistence.getManager().persist(pedido);
		XPersistence.commit();
	}

}
