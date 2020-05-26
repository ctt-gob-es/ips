package es.map.ipsg.bean;

import java.util.Date;

import es.map.ips.model.LogServicio;
import es.map.ips.model.SolicitudComun;

/**
 * RegistroSolicitudBean.
 *
 * @author amartinl
 */
public class RegistroSolicitudBean {
	
	/** el id. */
	private Long id;
	
	/** el solicitud. */
	private SolicitudComun solicitud;
	
	/** el log servicio. */
	private LogServicio logServicio;
	
	/** el fecha intento. */
	private Date fechaIntento;
	
	/** el resultado. */
	private String resultado;
	
	/** el fecha registro. */
	private Date fechaRegistro;
	
	/** el numero registro. */
	private String numeroRegistro;
	
	/** el solicitante. */
	private Character solicitante;
	
	/** el oficina registro. */
	private String oficinaRegistro;
	
	/** el codigo error. */
	private String codigoError;
	
	/** el descripcion error. */
	private String descripcionError;
	
	/** el consentimiento. */
	private Boolean consentimiento;
	
	/** el des consentimiento. */
	private String desConsentimiento;
	
	/** el id solicitud. */
	private Long idSolicitud;
	
	/**
	 * Obtiene el id.
	 *
	 * @return  id Long
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id Long
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el solicitud.
	 *
	 * @return solicitud Solicitud
	 */
	public SolicitudComun getSolicitud() {
		return solicitud;
	}
	
	/**
	 * Establece el solicitud.
	 *
	 * @param solicitud Solicitud
	 */
	public void setSolicitud(SolicitudComun solicitud) {
		this.solicitud = solicitud;
	}
	
	/**
	 * Obtiene el log servicio.
	 *
	 * @return  logServicio LogServicio
	 */ 
	public LogServicio getLogServicio() {
		return logServicio;
	}
	
	/**
	 * Establece el log servicio.
	 *
	 * @param logServicio LogServicio
	 */
	public void setLogServicio(LogServicio logServicio) {
		this.logServicio = logServicio;
	}
	
	/**
	 * Obtiene el fecha intento.
	 *
	 * @return fechaIntento Date
	 */
	public Date getFechaIntento() {
		return fechaIntento;
	}
	
	/**
	 * Establece el fecha intento.
	 *
	 * @param fechaIntento Date
	 */
	public void setFechaIntento(Date fechaIntento) {
		this.fechaIntento = fechaIntento;
	}
	
	/**
	 * Obtiene el resultado.
	 *
	 * @return resultado Date
	 */
	public String getResultado() {
		return resultado;
	}
	
	/**
	 * Establece el resultado.
	 *
	 * @param resultado Date
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	/**
	 * Obtiene el fecha registro.
	 *
	 * @return fechaRegistro Date
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	
	/**
	 * Establece el fecha registro.
	 *
	 * @param fechaRegistro Date
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	/**
	 * Obtiene el numero registro.
	 *
	 * @return numeroRegistro String
	 */
	public String getNumeroRegistro() {
		return numeroRegistro;
	}
	
	/**
	 * Establece el numero registro.
	 *
	 * @param numeroRegistro String
	 */
	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}
	
	/**
	 * Obtiene el solicitante.
	 *
	 * @return solicitante Character
	 */
	public Character getSolicitante() {
		return solicitante;
	}
	
	/**
	 * Establece el solicitante.
	 *
	 * @param solicitante Character
	 */
	public void setSolicitante(Character solicitante) {
		this.solicitante = solicitante;
	}
	
	/**
	 * Obtiene el oficina registro.
	 *
	 * @return oficinaRegistro String
	 */
	public String getOficinaRegistro() {
		return oficinaRegistro;
	}
	
	/**
	 * Establece el oficina registro.
	 *
	 * @param oficinaRegistro String
	 */
	public void setOficinaRegistro(String oficinaRegistro) {
		this.oficinaRegistro = oficinaRegistro;
	}
	
	/**
	 * Obtiene el codigo error.
	 *
	 * @return codigoError String
	 */
	public String getCodigoError() {
		return codigoError;
	}
	
	/**
	 * Establece el codigo error.
	 *
	 * @param codigoError String
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}
	
	/**
	 * Obtiene el descripcion error.
	 *
	 * @return  descripcionError String
	 */
	public String getDescripcionError() {
		return descripcionError;
	}
	
	/**
	 * Establece el descripcion error.
	 *
	 * @param descripcionError String
	 */
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}
	
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
	 * Obtiene el consentimiento.
	 *
	 * @return el consentimiento
	 */
	public Boolean getConsentimiento() {
		return consentimiento;
	}
	
	/**
	 * Establece el consentimiento.
	 *
	 * @param consentimiento el nuevo consentimiento
	 */
	public void setConsentimiento(Boolean consentimiento) {
		this.consentimiento = consentimiento;
	}
	
	/**
	 * Obtiene el des consentimiento.
	 *
	 * @return el des consentimiento
	 */
	public String getDesConsentimiento() {
		return desConsentimiento;
	}
	
	/**
	 * Establece el des consentimiento.
	 *
	 * @param desConsentimiento el nuevo des consentimiento
	 */
	public void setDesConsentimiento(String desConsentimiento) {
		this.desConsentimiento = desConsentimiento;
	}

}
