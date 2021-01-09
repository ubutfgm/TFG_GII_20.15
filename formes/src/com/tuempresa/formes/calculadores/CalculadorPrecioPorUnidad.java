package com.tuempresa.formes.calculadores;
import org.openxava.calculators.*;
import com.tuempresa.formes.modelo.*;
import static org.openxava.jpa.XPersistence.*; //Para usar getManager()
public class CalculadorPrecioPorUnidad implements ICalculator {

	private static final long serialVersionUID = 1L;
	private int numeroProducto;
    @Override
    public Object calculate() throws Exception {
        Producto producto = getManager() // getManager() de XPersistence
            .find(Producto.class, numeroProducto); // Busca el producto
        return producto.getPrecio();    // Retorna su precio
    }
 
    public int getNumeroProducto() {
        return numeroProducto;
    }
 
    public void setNumeroProducto(int numeroProducto) {
        this.numeroProducto = numeroProducto;
    }
}