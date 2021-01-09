package com.tuempresa.formes.modelo;

import java.math.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
public class facturasPendientes extends Identificable {

	@Required
	@Column(length = 50)
	private String numeroFactura;

	@Required
	@ManyToOne( // La referencia se almacena como una relación en la base de datos
			fetch = FetchType.LAZY, // La referencia se carga bajo demanda
			optional = true) // La referencia puede estar sin valor
	@DescriptionsList // Así la referencia se visualiza usando un combo
	private Marca marca; // Una referencia Java convencional

	@Required
	@Stereotype("FECHAHORA")
	private Date fechaVencimiento;

	@Required
	@Stereotype("DINERO")
	private BigDecimal cantidad;

	@Required
	@Column(length = 70)
	private String iban;

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

}
