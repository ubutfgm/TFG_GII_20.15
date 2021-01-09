package com.tuempresa.formes.modelo;

import java.math.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;

@Entity
@View(members = // Esta vista no tiene nombre, por tanto será la vista usada por defecto
"id , estaPedido;" + "productoPara;" + "importeCosteTotal")
public class Pedido extends Identificable {

	@Column(length = 6)
	private int id;

	private boolean estaPedido;

	@ElementCollection
	@ListProperties("producto.numero, producto.descripcion, cantidad, precioCostePorUnidad, " + "importe2+[" +

			"pedido.importeCosteTotal" + "]")
	private Collection<ProductoPara> productoPara;

	@ReadOnly
	@Stereotype("DINERO")
	@Calculation("sum(productoPara.importe2)")
	private BigDecimal importeCosteTotal;

	public int getId() {
		return id;
	}

	public void setId(int numero) {
		this.id = numero;
	}

	public boolean isEstaPedido() {
		return estaPedido;
	}

	public void setEstaPedido(boolean estaPedido) {
		this.estaPedido = estaPedido;
	}

	public Collection<ProductoPara> getProductoPara() {
		return productoPara;
	}

	public void setProductoPara(Collection<ProductoPara> productoPara) {
		this.productoPara = productoPara;
	}

	public BigDecimal getImporteCosteTotal() {
		System.out.println("Coste por unidad " + importeCosteTotal);
		return importeCosteTotal;
	}

	public void setImporteCosteTotal(BigDecimal importeCosteTotal) {
		this.importeCosteTotal = importeCosteTotal;
	}

	public static Pedido findActive() throws javax.ejb.ObjectNotFoundException {
		Query query = XPersistence.getManager().createQuery("select oid from Pedido  where  estaPedido = false");
		Pedido r = XPersistence.getManager().find(Pedido.class, query.getSingleResult());
		return r;
	}

}