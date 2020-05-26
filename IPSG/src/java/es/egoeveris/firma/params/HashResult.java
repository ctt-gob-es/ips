package es.egoeveris.firma.params;

import java.io.Serializable;

/**
 * El Class HashResult.
 */
public class HashResult implements Serializable{
	
	/** La constante serialVersionUID. */
	private static final long serialVersionUID = -5784201134471739410L;

	/** el hash datos. */
	private byte[] hashDatos;

	/** el error. */
	private boolean error;

	/** el descripcion. */
	private String descripcion;

	/**
	 * Obtiene el hash datos.
	 *
	 * @return the hashDatos
	 */
	public byte[] gethashDatos() {
		return hashDatos;
	}

	/**
	 * Establece el hash datos.
	 *
	 * @param hashDatos the hashDatos to set
	 */
	public void setHashDatos(byte[] hashDatos) {
		this.hashDatos = hashDatos;
	}

	/**
	 * Obtiene el error.
	 *
	 * @return the resultado
	 */
	public boolean getError() {
		return error;
	}

	/**
	 * Hash error.
	 *
	 * @return verdadero, si tiene exito
	 */
	public boolean hashError() {
		return error;
	}

	/**
	 * Establece el error.
	 *
	 * @param resultado the resultado to set
	 */
	public void setError(boolean resultado) {
		this.error = resultado;
	}

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
}
