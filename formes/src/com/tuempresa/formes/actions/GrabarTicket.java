package com.tuempresa.formes.actions;

import java.util.*;
import javax.naming.directory.*;
import org.openxava.actions.*;
import org.openxava.actions.SaveAction;
import org.openxava.jpa.*;
import com.tuempresa.formes.modelo.*;

/**
 * Esta clase es la modificacion de la accion por defecto para grabar un nuevo ticket. 
 * @author Antoni Lluis Garcia Exposito
 * @version 2.0
 */

public class GrabarTicket extends SaveAction implements IChainActionWithArgv {
	//Cammpos de la clase
	private String nextAction = null;
	private Collection<ProductoPara> productoPara;
	private int cantidad;
	private Producto producto;
	private tpv ticket;
 
	
		/**
		 * Esta sera la ejecucion de la accion, en esta caso hemos modificado el orden de realizacion de 
		 * las acciones que realizaba por defecto.. 
		 */
		public void execute() throws Exception {

			ticket = (tpv) getView().getEntity();
			productoPara = ticket.getProductoPara();
			
			for (ProductoPara lineaProductoPara : productoPara) {
			
				cantidad = lineaProductoPara.getCantidad();
				producto =  lineaProductoPara.getProducto();
				if(cantidad>producto.getCantidadTotal()) {
					throw new AttributeModificationException("Stock insuficient");
					//System.out.println("Stock insuficient");
					
				}
					
			}
			for (ProductoPara lineaProductoPara : productoPara) {
				cantidad = lineaProductoPara.getCantidad();
				producto =  XPersistence.getManager().find(Producto.class, lineaProductoPara.getProducto().getNumero()); 
				producto.setCantidadTotal(producto.getCantidadTotal()-cantidad);
				
			}
			ticket.anadirApedido();
			super.execute();
			
		}

		/**
		 * Metodo obligatorio de la clase extendida SaveAction, que al no tener la necesidad de usarlo lo hemos arreglado devolviendo 
		 * una variable inicializada a null.
		 */
		@Override
		public String getNextAction() throws Exception {
	               return nextAction;
		}
		/**
		 * Metodo obligatorio de la clase extendida SaveAction, que al no tener la necesidad de usarlo lo hemos arreglado devolviendo null.
		 */
		@Override
		public String getNextActionArgv() throws Exception {
			  return null;
		}

	
}
