package es.map.ipsg.form;

import es.map.ips.common.spring.SpringForm;

/**
 * El Class ExportaDocumentoForm.
 */
public class ExportaDocumentoForm extends SpringForm {

	/** el anexo. */
	private boolean anexo;
	
	/** el justifica pago. */
	private boolean justificaPago;
	
	/** el registro PDF. */
	private boolean registroPDF;
	
	/** el registro XML. */
	private boolean registroXML;
	
	/** el solicitudes sel. */
	private String[] solicitudesSel;
	
	/**
	 * Comprueba si es anexo.
	 *
	 * @return the anexo
	 */
	public boolean isAnexo() {
		return anexo;
	}
	
	/**
	 * Comprueba si es justifica pago.
	 *
	 * @return the justificaPago
	 */
	public boolean isJustificaPago() {
		return justificaPago;
	}
	
	/**
	 * Comprueba si es registro PDF.
	 *
	 * @return the registroPDF
	 */
	public boolean isRegistroPDF() {
		return registroPDF;
	}
	
	/**
	 * Comprueba si es registro XML.
	 *
	 * @return the registroXML
	 */
	public boolean isRegistroXML() {
		return registroXML;
	}
	
	/**
	 * Establece el anexo.
	 *
	 * @param anexo the anexo to set
	 */
	public void setAnexo(boolean anexo) {
		this.anexo = anexo;
	}
	
	/**
	 * Establece el justifica pago.
	 *
	 * @param justificaPago the justificaPago to set
	 */
	public void setJustificaPago(boolean justificaPago) {
		this.justificaPago = justificaPago;
	}
	
	/**
	 * Establece el registro PDF.
	 *
	 * @param registroPDF the registroPDF to set
	 */
	public void setRegistroPDF(boolean registroPDF) {
		this.registroPDF = registroPDF;
	}
	
	/**
	 * Establece el registro XML.
	 *
	 * @param registroXML the registroXML to set
	 */
	public void setRegistroXML(boolean registroXML) {
		this.registroXML = registroXML;
	}
	
	/**
	 * Obtiene el solicitudes sel.
	 *
	 * @return the solicitudesSel
	 */
	public String[] getSolicitudesSel() {
		return solicitudesSel;
	}
	
	/**
	 * Establece el solicitudes sel.
	 *
	 * @param solicitudesSel the solicitudesSel to set
	 */
	public void setSolicitudesSel(String[] solicitudesSel) {
		this.solicitudesSel = solicitudesSel;
	}
	
	
}
