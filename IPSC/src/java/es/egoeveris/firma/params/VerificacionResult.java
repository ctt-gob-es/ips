package es.egoeveris.firma.params;


import java.io.Serializable;

/**
 * El Class VerificacionResult.
 */
public class VerificacionResult implements Serializable {

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = -226029275584297640L;

	/** el error. */
	private boolean error;

	/** el descripcion. */
	private String descripcion;

	/** el estado verifica. */
	private String estadoVerifica;

	/** el items. */
	private VerificacionResultItem[] items;

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
	 * Hash error.
	 *
	 * @return the error
	 */
	public boolean hashError() {
		return error;
	}
	
	/**
	 * Obtiene el error.
	 *
	 * @return el error
	 */
	public boolean getError() {
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
	 * Obtiene el estado verifica.
	 *
	 * @return the estadoVerifica
	 */
	public String getEstadoVerifica() {
		return estadoVerifica;
	}

	/**
	 * Establece el estado verifica.
	 *
	 * @param estadoVerifica the estadoVerifica to set
	 */
	public void setEstadoVerifica(String estadoVerifica) {
		this.estadoVerifica = estadoVerifica;
	}

	/**
	 * Obtiene el resultado verificacion.
	 *
	 * @return the items
	 */
	public VerificacionResultItem[] getResultadoVerificacion() {
		return items;
	}

	/**
	 * Establece el resultado verificacion.
	 *
	 * @param items the items to set
	 */
	public void setResultadoVerificacion(VerificacionResultItem[] items) {
		this.items = items;
	}


}

