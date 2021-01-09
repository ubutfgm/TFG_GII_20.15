package com.tuempresa.formes.actions;

import java.util.*;



import javax.naming.directory.*;

import org.openxava.actions.*;
import org.openxava.actions.SaveAction;
import org.openxava.jpa.*;

import com.tuempresa.formes.modelo.*;
public class GrabarTicket extends SaveAction implements IChainActionWithArgv {

	private String nextAction = null;
	private Collection<ProductoPara> productoPara;
	private int cantidad;
	private Producto producto;
	private tpv ticket;
 
	
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


		@Override
		public String getNextAction() throws Exception {
	               return nextAction;
		}

		@Override
		public String getNextActionArgv() throws Exception {
			  return null;
		}

	
}
