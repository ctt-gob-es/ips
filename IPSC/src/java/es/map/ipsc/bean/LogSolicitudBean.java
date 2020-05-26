package es.map.ipsc.bean;

import java.util.Date;

/**
 * El Class LogSolicitudBean.
 */
public class LogSolicitudBean {
	
	/** el fecha. */
	private Date fecha;
	
	/** el tipo operacion. */
	private String tipoOperacion;
	
	/** el actor. */
	private String actor;
	
	/** el tipo actor. */
	private Character tipoActor;
	
	/** el detalle operacion. */
	private String detalleOperacion;
	
	/** el id solicitud. */
	private String idSolicitud;
	
	/** el id estado anterior. */
	private String idEstadoAnterior;
	
	/** el id estado actual. */
	private String idEstadoActual;
	
	
	/**
	 * Obtiene el fecha.
	 *
	 * @return el fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	
	/**
	 * Establece el fecha.
	 *
	 * @param fecha el nuevo fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * Obtiene el tipo operacion.
	 *
	 * @return el tipo operacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	
	/**
	 * Establece el tipo operacion.
	 *
	 * @param tipoOperacion el nuevo tipo operacion
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	
	/**
	 * Obtiene el actor.
	 *
	 * @return el actor
	 */
	public String getActor() {
		return actor;
	}
	
	/**
	 * Establece el actor.
	 *
	 * @param actor el nuevo actor
	 */
	public void setActor(String actor) {
		this.actor = actor;
	}
	
	/**
	 * Obtiene el tipo actor.
	 *
	 * @return el tipo actor
	 */
	public Character getTipoActor() {
		return tipoActor;
	}
	
	/**
	 * Establece el tipo actor.
	 *
	 * @param tipoActor el nuevo tipo actor
	 */
	public void setTipoActor(Character tipoActor) {
		this.tipoActor = tipoActor;
	}
	
	/**
	 * Obtiene el detalle operacion.
	 *
	 * @return el detalle operacion
	 */
	public String getDetalleOperacion() {
		return detalleOperacion;
	}
	
	/**
	 * Establece el detalle operacion.
	 *
	 * @param detalleOperacion el nuevo detalle operacion
	 */
	public void setDetalleOperacion(String detalleOperacion) {
		this.detalleOperacion = detalleOperacion;
	}
	
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
	 * Obtiene el id estado anterior.
	 *
	 * @return el id estado anterior
	 */
	public String getIdEstadoAnterior() {
		return idEstadoAnterior;
	}
	
	/**
	 * Establece el id estado anterior.
	 *
	 * @param idEstadoAnterior el nuevo id estado anterior
	 */
	public void setIdEstadoAnterior(String idEstadoAnterior) {
		this.idEstadoAnterior = idEstadoAnterior;
	}
	
	/**
	 * Obtiene el id estado actual.
	 *
	 * @return el id estado actual
	 */
	public String getIdEstadoActual() {
		return idEstadoActual;
	}
	
	/**
	 * Establece el id estado actual.
	 *
	 * @param idEstadoActual el nuevo id estado actual
	 */
	public void setIdEstadoActual(String idEstadoActual) {
		this.idEstadoActual = idEstadoActual;
	}
	
	
}
