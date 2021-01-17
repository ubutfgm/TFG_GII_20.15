package com.tuempresa.formes.actions;



import java.time.format.*;
import java.util.*;
import org.openxava.actions.*;
import com.tuempresa.formes.modelo.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;
/**
 * Esta clase es la modificacion de la accion por defecto para la impresion de un nuevo ticket/reporte. 
 * @author Antoni Lluis Garcia Exposito
 * @version 2.0
 */
public class ImpresionTicket extends JasperReportBaseAction {
	tpv ticket;
	private String argumento;
	
	/**
	 * 
	 * @return el argumento  del report.
	 */
	public String getArgumento() {
	     return argumento;
	   }
	/**
	 * En este metodo lo que hacemos es mapear la entidad tpv para recoger los parametros necesarios para rellenar nuestro ticket/factura.
	 */
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
		/**
		 * Metodo que sobreescribe al de por defecto para poder obtener nuestro informe y no el de por defecto.
		 */
		@Override
	    protected String getJRXML() throws Exception {
	    return "ticket.jrxml";
	  
	    }
		/**
		 * No todos los datos son parametros, en el caso de la obtencion de los campos duna colleccion, se recogen con 
		 * JRBeanCollectionDataSource.
		 */
	    protected JRDataSource getDataSource() throws Exception {
	        return new JRBeanCollectionDataSource(getTicket().getProductoPara());
	    }
	    
	    /**
	     * Metodo para inicializar un ticket con los datos de la entidad tpv de ese instante. para asi poderlos imprimir.
	     * @return nos devuelve un ticket.
	     * @throws Exception
	     */
	    private tpv getTicket() throws Exception{
	    	return ticket = (tpv) getView().getEntity();
	    }
	    
	    public void setArgumento(String argumento) {
	        this.argumento = argumento;
	    }
	 
}
