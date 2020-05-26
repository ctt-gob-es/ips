package es.egoeveris.firma.params;

import java.io.Serializable;


/**
 * El Class FirmaResult.
 */
public class FirmaResult implements Serializable {

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 4065496280838828824L;

	/** el error. */
	private boolean error;

	/** el descripcion. */
	private String descripcion;

	/** el firma. */
	private String firma;

	/** el id documento. */
	private String idDocumento;

	/** el id transaccion. */
	private long idTransaccion;

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
	 * Obtiene el error.
	 *
	 * @return the error
	 */
	public boolean getError() {
		return error;
	}

	/**
	 * Hash error.
	 *
	 * @return verdadero, si tiene exito
	 */
	public boolean hashError(){
		return error;
	}

	/**
	 * Establece el error.
	 *
	 * @param error the error to set
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * Obtiene el firma.
	 *
	 * @return the firma
	 */
	public String getFirma() {
		return firma;
	}

	/**
	 * Establece el firma.
	 *
	 * @param firma the firma to set
	 */
	public void setFirma(String firma) {
		this.firma = firma;
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
	 * Obtiene el id transaccion.
	 *
	 * @return the idTransaccion
	 */
	public long getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * Establece el id transaccion.
	 *
	 * @param idTransaccion the idTransaccion to set
	 */
	public void setIdTransaccion(long idTransaccion) {
		this.idTransaccion = idTransaccion;
	} 

	
}

