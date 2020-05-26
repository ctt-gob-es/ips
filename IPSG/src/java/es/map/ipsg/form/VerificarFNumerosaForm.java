package es.map.ipsg.form;

import es.map.ips.common.spring.SpringForm;

/**
 * El Class VerificarFNumerosaForm.
 */
public class VerificarFNumerosaForm extends SpringForm {

	/** el id solicitud. */
	private Long idSolicitud;
	
	/** el accion. */
	private String accion;
	
	/** el categoria F numerosa. */
	private String categoriaFNumerosa;
	
	/** el solicitante F numerosa. */
	private String solicitanteFNumerosa;
	
	/** el vigencia titulo. */
	private String vigenciaTitulo;
	
	/** el descripcion error. */
	private String descripcionError;
	

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
	 * Obtiene el categoria F numerosa.
	 *
	 * @return el categoria F numerosa
	 */
	public String getCategoriaFNumerosa() {
		return categoriaFNumerosa;
	}
	
	/**
	 * Establece el categoria F numerosa.
	 *
	 * @param categoriaFNumerosa el nuevo categoria F numerosa
	 */
	public void setCategoriaFNumerosa(String categoriaFNumerosa) {
		this.categoriaFNumerosa = categoriaFNumerosa;
	}
	
	/**
	 * Obtiene el solicitante F numerosa.
	 *
	 * @return el solicitante F numerosa
	 */
	public String getSolicitanteFNumerosa() {
		return solicitanteFNumerosa;
	}
	
	/**
	 * Establece el solicitante F numerosa.
	 *
	 * @param solicitanteFNumerosa el nuevo solicitante F numerosa
	 */
	public void setSolicitanteFNumerosa(String solicitanteFNumerosa) {
		this.solicitanteFNumerosa = solicitanteFNumerosa;
	}
	
	/**
	 * Obtiene el vigencia titulo.
	 *
	 * @return el vigencia titulo
	 */
	public String getVigenciaTitulo() {
		return vigenciaTitulo;
	}
	
	/**
	 * Establece el vigencia titulo.
	 *
	 * @param vigenciaTitulo el nuevo vigencia titulo
	 */
	public void setVigenciaTitulo(String vigenciaTitulo) {
		this.vigenciaTitulo = vigenciaTitulo;
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
