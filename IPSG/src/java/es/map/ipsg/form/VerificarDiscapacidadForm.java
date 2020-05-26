package es.map.ipsg.form;

import es.map.ips.common.spring.SpringForm;

/**
 * El Class VerificarDiscapacidadForm.
 */
public class VerificarDiscapacidadForm extends SpringForm {

	/** el id solicitud. */
	private Long idSolicitud;
	
	/** el accion. */
	private String accion;
	
	/** el grado discapacidad. */
	private Integer gradoDiscapacidad;
	
	/** el resultado verificacion. */
	private boolean resultadoVerificacion;

	/**
	 * Obtiene el id solicitud.
	 *
	 * @return el id solicitud
	 */
	public Long getIdSolicitud() {
		return idSolicitud;
	}
	
	/**
	 * Establece el id solicitud.
	 *
	 * @param idSolicitud el nuevo id solicitud
	 */
	public void setIdSolicitud(Long idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	
	/**
	 * Obtiene el accion.
	 *
	 * @return el accion
	 */
	public String getAccion() {
		return accion;
	}
	
	/**
	 * Establece el accion.
	 *
	 * @param accion el nuevo accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	/**
	 * Obtiene el grado discapacidad.
	 *
	 * @return el grado discapacidad
	 */
	public Integer getGradoDiscapacidad() {
		return gradoDiscapacidad;
	}
	
	/**
	 * Establece el grado discapacidad.
	 *
	 * @param gradoDiscapacidad el nuevo grado discapacidad
	 */
	public void setGradoDiscapacidad(Integer gradoDiscapacidad) {
		this.gradoDiscapacidad = gradoDiscapacidad;
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
}
	