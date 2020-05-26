package es.egoeveris.firma.params;

import java.io.Serializable;

/**
 * El Class FirmaServidorResult.
 */
public class FirmaServidorResult implements Serializable{

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** el error. */
	private boolean error;

	/** el estado. */
	private String estado;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el id transaccion. */
	private String idTransaccion;
	
	/** el formato firma. */
	private String formatoFirma;
	
	/** el id documento. */
	private String idDocumento;
	
	/** el firma electronica. */
	private String firmaElectronica;

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
	 * @param estado el nuevo estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Obtiene el descripcion.
	 *
	 * @return el descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece el descripcion.
	 *
	 * @param descripcion el nuevo descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtiene el id transaccion.
	 *
	 * @return el id transaccion
	 */
	public String getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * Establece el id transaccion.
	 *
	 * @param idTransaccion el nuevo id transaccion
	 */
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	/**
	 * Obtiene el formato firma.
	 *
	 * @return el formato firma
	 */
	public String getFormatoFirma() {
		return formatoFirma;
	}

	/**
	 * Establece el formato firma.
	 *
	 * @param formatoFirma el nuevo formato firma
	 */
	public void setFormatoFirma(String formatoFirma) {
		this.formatoFirma = formatoFirma;
	}

	/**
	 * Obtiene el id documento.
	 *
	 * @return el id documento
	 */
	public String getIdDocumento() {
		return idDocumento;
	}

	/**
	 * Establece el id documento.
	 *
	 * @param idDocumento el nuevo id documento
	 */
	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * Obtiene el firma electronica.
	 *
	 * @return el firma electronica
	 */
	public String getFirmaElectronica() {
		return firmaElectronica;
	}

	/**
	 * Establece el firma electronica.
	 *
	 * @param firmaElectronica el nuevo firma electronica
	 */
	public void setFirmaElectronica(String firmaElectronica) {
		this.firmaElectronica = firmaElectronica;
	}




}
