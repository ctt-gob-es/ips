package es.map.ipsg.bean;

import es.map.ipsg.clienteWS.registroRec3.RegistroType;
import es.map.ipsg.clienteWS.registroRec3.type.JustificanteType;


/**
 * El Class GenerarJustificanteBean.
 */
public class GenerarJustificanteBean {

	/** el msg error. */
	private String msgError;
	
	/** el doc CSV firma sinbase 64. */
	private byte[] docCSVFirmaSinbase64;
	
	/** el registro solicitud justificante. */
	private RegistroSolicitudJustificanteBean registroSolicitudJustificante;
	
	/** el justificante type. */
	private JustificanteType justificanteType;
	
	/** el registro type. */
	private RegistroType registroType;
	
	/** el consentimiento. */
	private String consentimiento;
	
	/**
	 * Obtiene el msg error.
	 *
	 * @return el msg error
	 */
	public String getMsgError() {
		return msgError;
	}
	
	/**
	 * Establece el msg error.
	 *
	 * @param msgError el nuevo msg error
	 */
	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}
	
	/**
	 * Obtiene el doc CSV firma sinbase 64.
	 *
	 * @return el doc CSV firma sinbase 64
	 */
	public byte[] getDocCSVFirmaSinbase64() {
		return docCSVFirmaSinbase64;
	}
	
	/**
	 * Establece el doc CSV firma sinbase 64.
	 *
	 * @param docCSVFirmaSinbase64 el nuevo doc CSV firma sinbase 64
	 */
	public void setDocCSVFirmaSinbase64(byte[] docCSVFirmaSinbase64) {
		this.docCSVFirmaSinbase64 = docCSVFirmaSinbase64;
	}
	
	/**
	 * Obtiene el registro solicitud justificante.
	 *
	 * @return el registro solicitud justificante
	 */
	public RegistroSolicitudJustificanteBean getRegistroSolicitudJustificante() {
		return registroSolicitudJustificante;
	}
	
	/**
	 * Establece el registro solicitud justificante.
	 *
	 * @param registroSolicitudJustificante el nuevo registro solicitud justificante
	 */
	public void setRegistroSolicitudJustificante(
			RegistroSolicitudJustificanteBean registroSolicitudJustificante) {
		this.registroSolicitudJustificante = registroSolicitudJustificante;
	}
	
	/**
	 * Obtiene el justificante type.
	 *
	 * @return el justificante type
	 */
	public JustificanteType getJustificanteType() {
		return justificanteType;
	}
	
	/**
	 * Establece el justificante type.
	 *
	 * @param justificanteType el nuevo justificante type
	 */
	public void setJustificanteType(JustificanteType justificanteType) {
		this.justificanteType = justificanteType;
	}
	
	/**
	 * Obtiene el registro type.
	 *
	 * @return el registro type
	 */
	public RegistroType getRegistroType() {
		return registroType;
	}
	
	/**
	 * Establece el registro type.
	 *
	 * @param registroType el nuevo registro type
	 */
	public void setRegistroType(RegistroType registroType) {
		this.registroType = registroType;
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
