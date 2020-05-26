package es.map.ipsg.bean;

import java.util.Date;

/**
 * El Class IntermediacionIdentidadBean.
 */
public class IntermediacionIdentidadBean {
	
	/** el fecha nacimiento. */
	private Date fechaNacimiento;
	
	/** el resultado verificacion. */
	private boolean resultadoVerificacion;
	
	/** el descripcion error. */
	private String descripcionError;

	/**
	 * Obtiene el fecha nacimiento.
	 *
	 * @return el fecha nacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Establece el fecha nacimiento.
	 *
	 * @param fechaNacimiento el nuevo fecha nacimiento
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Comprueba si es resultado verificacion.
	 *
	 * @return verdadero, si es resultado verificacion
	 */
	public boolean isResultadoVerificacion() {
		return resultadoVerificacion;
	}

	/**
	 * Establece el resultado verificacion.
	 *
	 * @param resultadoVerificacion el nuevo resultado verificacion
	 */
	public void setResultadoVerificacion(boolean resultadoVerificacion) {
		this.resultadoVerificacion = resultadoVerificacion;
	}

	/**
	 * Obtiene el descripcion error.
	 *
	 * @return el descripcion error
	 */
	public String getDescripcionError() {
		return descripcionError;
	}

	/**
	 * Establece el descripcion error.
	 *
	 * @param descripcionError el nuevo descripcion error
	 */
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}
	
}
