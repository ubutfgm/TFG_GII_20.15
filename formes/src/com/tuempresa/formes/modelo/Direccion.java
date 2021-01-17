package com.tuempresa.formes.modelo;

import javax.persistence.*;
/**
 * Esta entidad esta basada en la entidad Direccion que entra por defecto en OpenXava, lo que he modificado los campos a mi gusto para
 * la obtencion de los campos que necesito.
 * @author Antoni Lluis Garcia Exposito
 *
 */
@Embeddable 
public class Direccion {

	@Column(length = 30) 
	private String viaPublica;

	@Column(length = 5)
	private int codigoPostal;

	@Column(length = 20)
	private String municipio;

	@Column(length = 30)
	private String provincia;
	/**
	 * Get para obtener la dirección de la via en la que vive.
	 * @return nombre de la via.
	 */
	public String getViaPublica() {
		return viaPublica;
	}
	/**
	 * Set para añadir la via Publica.
	 * @param viaPublica
	 */
	public void setViaPublica(String viaPublica) {
		this.viaPublica = viaPublica;
	}
	/**
	 * Get para obtener el codigo postal.
	 * @return Codigo poastal.
	 */
	public int getCodigoPostal() {
		return codigoPostal;
	}
	/**
	 * Set para añadir el codigo postal
	 * @param codigoPostal
	 */
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	/**
	 * Get para obtener el municipio.
	 * @return El municipio .
	 */
	public String getMunicipio() {
		return municipio;
	}
	/**
	 * Set para añadir el municipio.
	 * @param municipio
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	/**
	 * Get para obtener la provincia.
	 * @return La provincia.
	 */
	public String getProvincia() {
		return provincia;
	}
	/**
	 * Set para añadir la provincia.
	 * @param provincia
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

}