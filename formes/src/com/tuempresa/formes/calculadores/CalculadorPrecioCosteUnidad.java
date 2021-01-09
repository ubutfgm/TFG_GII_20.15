package com.tuempresa.formes.calculadores;

import static org.openxava.jpa.XPersistence.*;

import org.openxava.calculators.*;

import com.tuempresa.formes.modelo.*;

public class CalculadorPrecioCosteUnidad  implements ICalculator {
	private static final long serialVersionUID = 1L;
	private int numeroProducto;
    @Override
    public Object calculate() throws Exception {
        Producto producto = getManager() // getManager() de XPersistence
            .find(Producto.class, numeroProducto); // Busca el producto
        return producto.getPrecioCoste();    // Retorna su precio de coste
    }
    public int getNumeroProducto() {
        return numeroProducto;
    }
 
    public void setNumeroProducto(int numeroProducto) {
        this.numeroProducto = numeroProducto;
    }
}

