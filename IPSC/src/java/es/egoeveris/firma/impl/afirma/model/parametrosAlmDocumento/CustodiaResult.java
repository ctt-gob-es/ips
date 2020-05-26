package es.egoeveris.firma.impl.afirma.model.parametrosAlmDocumento;

import java.io.Serializable;

/**
 * El Class CustodiaResult.
 */
public class CustodiaResult implements Serializable{

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 4067554678494674950L;

	/** el descripcion. */
	private String descripcion;
	
	/** el estado. */
	private String estado;

	/** el id documento. */
	private String idDocumento;
	
	/** el error. */
	private boolean error;

	/**
	 * Obtiene el descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece el descripcion.
	 *
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	/**
	 * Obtiene el estado.
	 *
	 * @return el estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Establece el estado.
	 *
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Obtiene el id documento.
	 *
	 * @return the idDocumento
	 */
	public String getIdDocumento() {
		return idDocumento;
	}

	/**
	 * Establece el id documento.
	 *
	 * @param idDocumento the idDocumento to set
	 */
	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * Establece el error.
	 *
	 * @param error el nuevo error
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * Comprueba si es error.
	 *
	 * @return verdadero, si es error
	 */
	public boolean isError() {
		return error;
	}



}
