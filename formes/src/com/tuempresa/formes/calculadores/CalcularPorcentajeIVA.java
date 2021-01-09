package com.tuempresa.formes.calculadores;

import org.openxava.calculators.*;
import com.tuempresa.formes.util.*;
public class CalcularPorcentajeIVA implements ICalculator {

	private static final long serialVersionUID = 1L;
	@Override
	public Object calculate() throws Exception {
		return PreferenciasFormes.getPorcentajeIVADefecto();
	}

}
