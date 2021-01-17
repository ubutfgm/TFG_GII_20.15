package com.tuempresa.formes.modelo;

import javax.persistence.*;
import org.hibernate.annotations.*;
import org.openxava.annotations.*;
/**
 * 
 *Esta clase esta basada en la clase del tutorial de OpenXava.
 *
 */
@MappedSuperclass // Marcada como una superclase mapeada en vez de como una entidad
public class Identificable {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Hidden
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)
	private String oid; // La definición de propiedad incluye anotaciones de OpenXava y JPA

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

}