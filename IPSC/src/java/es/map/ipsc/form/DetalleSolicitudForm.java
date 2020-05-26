package es.map.ipsc.form;


import es.map.ips.common.spring.SpringForm;


/**
 * El Class DetalleSolicitudForm.
 */
public class DetalleSolicitudForm extends SpringForm {

	/** el registro. */
	private String registro;
	
	/** el id solicitud. */
	private String idSolicitud;
	
	/** el consentimiento. */
	private String consentimiento;
	
	

	/**
	 * Obtiene el id solicitud.
	 *
	 * @return el id solicitud
	 */
	public String getIdSolicitud() {
		return idSolicitud;
	}

	/**
	 * Establece el id solicitud.
	 *
	 * @param idSolicitud el nuevo id solicitud
	 */
	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	/**
	 * Obtiene el registro.
	 *
	 * @return el registro
	 */
	public String getRegistro() {
		return registro;
	}

	/**
	 * Establece el registro.
	 *
	 * @param registro el nuevo registro
	 */
	public void setRegistro(String registro) {
		this.registro = registro;
	}

	/**
	 * Obtiene el consentimiento.
	 *
	 * @return el consentimiento
	 */
	public String getConsentimiento() {
		return consentimiento;
	}

	/**
	 * Establece el consentimiento.
	 *
	 * @param consentimiento el nuevo consentimiento
	 */
	public void setConsentimiento(String consentimiento) {
		this.consentimiento = consentimiento;
	}
	
	
}
