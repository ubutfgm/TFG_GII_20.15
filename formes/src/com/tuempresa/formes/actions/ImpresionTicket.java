package com.tuempresa.formes.actions;



import java.time.format.*;
import java.util.*;

import org.openxava.actions.*;


import com.tuempresa.formes.modelo.*;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;

public class ImpresionTicket extends JasperReportBaseAction {
	tpv ticket;
	private String argumento;
	   public String getArgumento() {
	     return argumento;
	   }
	
	public Map<String, Object> getParameters() throws Exception  {
        Map<String, Object> parametros = new HashMap<String, Object>(); 
        ticket = getTicket();
		parametros.put("numero", ticket.getNumero());
		parametros.put("fecha", ticket.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		parametros.put("cliente", ticket.getCliente().getNombre() );
		parametros.put("cif", ticket.getCliente().getCIF());
		parametros.put("iva", ticket.getIva());
		parametros.put("total", ticket.getImporteTotal());
        return parametros;
    }
		@Override
	    protected String getJRXML() throws Exception {
	    return "ticket.jrxml";
	  
	    }
	    protected JRDataSource getDataSource() throws Exception {
	        return new JRBeanCollectionDataSource(getTicket().getProductoPara());
	    }
	    
	    private tpv getTicket() throws Exception{
	    	return ticket = (tpv) getView().getEntity();
	    }
	    public void setArgumento(String argumento) {
	        this.argumento = argumento;
	    }
	 
}
