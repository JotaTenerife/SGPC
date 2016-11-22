package es.llamalox.sgpc.model;

import java.util.Date;

/**
 * Esta interfaz se encarga de que todos los modelos contengan los métodos
 * mínimos usados por los genéricos usados en el sistema.
 */
public interface Accesible {

	// Identificador interno del modelo
	Integer getId();

	void setId(Integer id);

	// Identificador público del modelo
	String getCodigo();

	void setCodigo(String codigo);

	// Borrado lógico del modelo
	Date getFechaBaja();

	void setFechaBaja(Date fechaBaja);

	boolean isActivo();
}
