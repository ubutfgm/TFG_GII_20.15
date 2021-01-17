package com.tuempresa.formes.modelo;

import java.math.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;
/**
 * Es una de las entidades importantes, ya que en ella se iran añadiendo los productos que se esten quedando sin stock.
 * @author Antoni Lluis Garcia Exposito
 *
 */
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
	/**
	 * 
	 * @return el id del pedido.
	 */
	public int getId() {
		return id;
	}
	/**
	 * Añadimos el numero del pedido.
	 * @param numero
	 */
	public void setId(int numero) {
		this.id = numero;
	}
	/**
	 * 
	 * @return true si esta pedido, false si el pedido esta abierto.
	 */
	public boolean isEstaPedido() {
		return estaPedido;
	}
	/**
	 * Definimos el estado del pedido.
	 * @param estaPedido
	 */
	public void setEstaPedido(boolean estaPedido) {
		this.estaPedido = estaPedido;
	}
	/**
	 * 
	 * @return la coleccion de Productos que tendra el pedido.
	 */
	public Collection<ProductoPara> getProductoPara() {
		return productoPara;
	}
	/**
	 * Añadimos la colección de pProductos a nuestro pedido.
	 * @param productoPara
	 */
	public void setProductoPara(Collection<ProductoPara> productoPara) {
		this.productoPara = productoPara;
	}
	/**
	 * 
	 * @return el precio total del pedido.
	 */
	public BigDecimal getImporteCosteTotal() {
		return importeCosteTotal;
	}
	/**
	 * Añadimos el coste total del pedido.
	 * @param importeCosteTotal
	 */
	public void setImporteCosteTotal(BigDecimal importeCosteTotal) {
		this.importeCosteTotal = importeCosteTotal;
	}
	/**
	 * Este es uno de los metodos en version 1.0, se encargara de devolvernos el pedido que este abierto. De momento solo puede haber uno y
	 * lo tiene que gestionar el usuario.
	 * @return el pedido que esta abierto.
	 * @throws javax.ejb.ObjectNotFoundException
	 */
	public static Pedido findActive() throws javax.ejb.ObjectNotFoundException {
		Query query = XPersistence.getManager().createQuery("select oid from Pedido  where  estaPedido = false");
		Pedido r = XPersistence.getManager().find(Pedido.class, query.getSingleResult());
		return r;
	}

}