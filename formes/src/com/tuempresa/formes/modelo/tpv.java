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

	public Collection<ProductoPara> getProductoPara() {
		return productoPara;
	}

	public void setProductoPara(Collection<ProductoPara> productoPara) {
		this.productoPara = productoPara;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getPorcentajeIVA() {
		return porcentajeIVA;
	}

	public void setPorcentajeIVA(BigDecimal porcentajeIVA) {
		this.porcentajeIVA = porcentajeIVA;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	public BigDecimal getPagaCon() {
		return pagaCon;
	}

	public void setPagaCon(BigDecimal pagaCon) {
		this.pagaCon = pagaCon;
	}

	public BigDecimal getResto() {
		return resto;
	}

	public void setResto(BigDecimal resto) {
		this.resto = resto;
	}

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
