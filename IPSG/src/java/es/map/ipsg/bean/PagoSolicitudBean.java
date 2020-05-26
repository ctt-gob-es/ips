package es.map.ipsg.bean;

import java.util.Date;

import es.map.ips.model.EntidadFinanciera;
import es.map.ips.model.LogServicio;
import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.SolicitudComun;


/**
 * TipoSolicitudBean.
 *
 * @author amartinl
 */
public class PagoSolicitudBean {
	
	/** el id. */
	private Long id;
	
	/** el solicitud. */
	private SolicitudComun solicitud;
	
	/** el fecha intento. */
	private Date fechaIntento;
	
	/** el tipo. */
	private Character tipo;
	
	/** el importe. */
	private Float importe;
	
	/** el resultado. */
	private String resultado = "";
	
	/** el logservicio. */
	private LogServicio logservicio;
	
	/** el solicita reduccion. */
	private Character solicitaReduccion; 
	
	/** el motivo reduccion tasa. */
	private MotivoReduccionTasa motivoReduccionTasa;
	
	/** el entidad financiera. */
	private EntidadFinanciera entidadFinanciera;
	
	/** el nrc. */
	private String nrc ="";
	
	/** el cod error. */
	private String codError;
	
	/** el des error. */
	private String desError;
	
	/** el des motivo reduccion tasa. */
	private String desMotivoReduccionTasa;
	
	/** el des entidad financiera. */
	private String desEntidadFinanciera;
	
	/** el id solicitud. */
	private Long idSolicitud;
	
	/** el cod entidad financiera. */
	private String codEntidadFinanciera;
	
	
	


	/**
	 * Obtiene el cod entidad financiera.
	 *
	 * @return el cod entidad financiera
	 */
	public String getCodEntidadFinanciera() {
		return codEntidadFinanciera;
	}
	
	/**
	 * Establece el cod entidad financiera.
	 *
	 * @param codEntidadFinanciera el nuevo cod entidad financiera
	 */
	public void setCodEntidadFinanciera(String codEntidadFinanciera) {
		this.codEntidadFinanciera = codEntidadFinanciera;
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
	 * Obtiene el des motivo reduccion tasa.
	 *
	 * @return el des motivo reduccion tasa
	 */
	public String getDesMotivoReduccionTasa() {
		return desMotivoReduccionTasa;
	}
	
	/**
	 * Establece el des motivo reduccion tasa.
	 *
	 * @param desMotivoReduccionTasa el nuevo des motivo reduccion tasa
	 */
	public void setDesMotivoReduccionTasa(String desMotivoReduccionTasa) {
		this.desMotivoReduccionTasa = desMotivoReduccionTasa;
	}
	
	/**
	 * Obtiene el des entidad financiera.
	 *
	 * @return el des entidad financiera
	 */
	public String getDesEntidadFinanciera() {
		return desEntidadFinanciera;
	}
	
	/**
	 * Establece el des entidad financiera.
	 *
	 * @param desEntidadFinanciera el nuevo des entidad financiera
	 */
	public void setDesEntidadFinanciera(String desEntidadFinanciera) {
		this.desEntidadFinanciera = desEntidadFinanciera;
	}
	/** 
	 * @return id Long
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
	 * Obtiene el tipo.
	 *
	 * @return tipo Character
	 */
	public Character getTipo() {
		return tipo;
	}
	
	/**
	 * Establece el tipo.
	 *
	 * @param tipo Character
	 */
	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Obtiene el importe.
	 *
	 * @return importe Float
	 */
	public Float getImporte() {
		return importe;
	}
	
	/**
	 * Establece el importe.
	 *
	 * @param importe Long
	 */
	public void setImporte(Float importe) {
		this.importe = importe;
	}
	
	/**
	 * Obtiene el resultado.
	 *
	 * @return resultado String
	 */
	public String getResultado() {
		return resultado;
	}
	
	/**
	 * Establece el resultado.
	 *
	 * @param resultado String
	 */ 
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	/**
	 * Obtiene el logservicio.
	 *
	 * @return logservicio LogServicio
	 */
	public LogServicio getLogservicio() {
		return logservicio;
	}
	
	/**
	 * Establece el logservicio.
	 *
	 * @param logservicio LogServicio
	 */
	public void setLogservicio(LogServicio logservicio) {
		this.logservicio = logservicio;
	}
	
	/**
	 * Obtiene el solicita reduccion.
	 *
	 * @return solicitaReduccion Character
	 */
	public Character getSolicitaReduccion() {
		return solicitaReduccion;
	}
	
	/**
	 * Establece el solicita reduccion.
	 *
	 * @param solicitaReduccion Character
	 */
	public void setSolicitaReduccion(Character solicitaReduccion) {
		this.solicitaReduccion = solicitaReduccion;
	}
	
	/**
	 * Obtiene el motivo reduccion tasa.
	 *
	 * @return motivoReduccionTasa MotivoReduccionTasa
	 */
	public MotivoReduccionTasa getMotivoReduccionTasa() {
		return motivoReduccionTasa;
	}
	
	/**
	 * Establece el motivo reduccion tasa.
	 *
	 * @param motivoReduccionTasa MotivoReduccionTasa
	 */
	public void setMotivoReduccionTasa(MotivoReduccionTasa motivoReduccionTasa) {
		this.motivoReduccionTasa = motivoReduccionTasa;
	}
	
	/**
	 * Obtiene el entidad financiera.
	 *
	 * @return entidadFinanciera EntidadFinanciera
	 */
	public EntidadFinanciera getEntidadFinanciera() {
		return entidadFinanciera;
	}
	
	/**
	 * Establece el entidad financiera.
	 *
	 * @param entidadFinanciera EntidadFinanciera
	 */
	public void setEntidadFinanciera(EntidadFinanciera entidadFinanciera) {
		this.entidadFinanciera = entidadFinanciera;
	}
	
	/**
	 * Obtiene el nrc.
	 *
	 * @return nrc String
	 */
	public String getNrc() {
		return nrc;
	}
	
	/**
	 * Establece el nrc.
	 *
	 * @param nrc String
	 */
	public void setNrc(String nrc) {
		this.nrc = nrc;
	}
	
	/**
	 * Obtiene el cod error.
	 *
	 * @return el cod error
	 */
	public String getCodError() {
		return codError;
	}
	
	/**
	 * Establece el cod error.
	 *
	 * @param codError el nuevo cod error
	 */
	public void setCodError(String codError) {
		this.codError = codError;
	}
	
	/**
	 * Obtiene el des error.
	 *
	 * @return el des error
	 */
	public String getDesError() {
		return desError;
	}
	
	/**
	 * Establece el des error.
	 *
	 * @param desError el nuevo des error
	 */
	public void setDesError(String desError) {
		this.desError = desError;
	}
	


}
