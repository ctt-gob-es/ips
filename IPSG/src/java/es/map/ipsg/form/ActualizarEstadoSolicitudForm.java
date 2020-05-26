package es.map.ipsg.form;

import es.map.ips.common.spring.SpringForm;

/**
 * El Class ActualizarEstadoSolicitudForm.
 */
public class ActualizarEstadoSolicitudForm extends SpringForm {

	/** el id solicitud. */
	private Long idSolicitud;
	
	/** el estado actual. */
	private String estadoActual;		
	
	/** el nuevo estado. */
	private String nuevoEstado;
	
	/** el id nuevo estado. */
	private Integer idNuevoEstado;
	
	/** el submit. */
	private String submit;	
	
	/**
	 * Obtiene el id solicitud.
	 *
	 * @return the idSolicitud
	 */
	public Long getIdSolicitud() {
		return idSolicitud;
	}
	
	/**
	 * Establece el id solicitud.
	 *
	 * @param idSolicitud the idSolicitud to set
	 */
	public void setIdSolicitud(Long idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	
	/**
	 * Obtiene el estado actual.
	 *
	 * @return the estadoActual
	 */
	public String getEstadoActual() {
		return estadoActual;
	}
	
	/**
	 * Establece el estado actual.
	 *
	 * @param estadoActual the estadoActual to set
	 */
	public void setEstadoActual(String estadoActual) {
		this.estadoActual = estadoActual;
	}
	
	/**
	 * Obtiene el nuevo estado.
	 *
	 * @return the nuevoEstado
	 */
	public String getNuevoEstado() {
		return nuevoEstado;
	}
	
	/**
	 * Establece el nuevo estado.
	 *
	 * @param nuevoEstado the nuevoEstado to set
	 */
	public void setNuevoEstado(String nuevoEstado) {
		this.nuevoEstado = nuevoEstado;
	}
	
	/**
	 * Obtiene el id nuevo estado.
	 *
	 * @return the idNuevoEstado
	 */
	public Integer getIdNuevoEstado() {
		return idNuevoEstado;
	}
	
	/**
	 * Establece el id nuevo estado.
	 *
	 * @param idNuevoEstado the idNuevoEstado to set
	 */
	public void setIdNuevoEstado(Integer idNuevoEstado) {
		this.idNuevoEstado = idNuevoEstado;
	}
	
	/**
	 * Obtiene el submit.
	 *
	 * @return the submit
	 */
	public String getSubmit() {
		return submit;
	}
	
	/**
	 * Establece el submit.
	 *
	 * @param submit the submit to set
	 */
	public void setSubmit(String submit) {
		this.submit = submit;
	}
}
