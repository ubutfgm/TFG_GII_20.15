package com.tuempresa.formes.calculadores;

import org.openxava.calculators.*;

import com.tuempresa.formes.util.*;
/**
 * 
 * Esta clase no esta creada por mi sino que te la da el propio OpenXava, pero la he adaptado a mis necesidades
 * para el calculo del valor por defecto del IVA.
 *
 */
public class CalcularPorcentajeIVA implements ICalculator {

	private static final long serialVersionUID = 1L;
	@Override
	public Object calculate() throws Exception {
		return PreferenciasFormes.getPorcentajeIVADefecto();
	}

}
