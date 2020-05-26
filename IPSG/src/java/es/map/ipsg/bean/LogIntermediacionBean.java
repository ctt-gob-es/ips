package es.map.ipsg.bean;

import java.util.Date;

import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunAuxiliar;

/**
 * El Class LogIntermediacionBean.
 */
public class LogIntermediacionBean {

	/** el id log intermediacion. */
	private Integer idLogIntermediacion;
	
	/** el solicitud comun. */
	private SolicitudComun solicitudComun;
	
	private SolicitudComunAuxiliar solicitudComunAuxiliar;
	
	/** el id peticion. */
	private String idPeticion;
	
	/** el fecha. */
	private Date fecha;
	
	/** el resultado. */
	private String resultado;
	
	/** el descripcion. */
	private String descripcion;
	
	
	/**
	 * Obtiene el id log intermediacion.
	 *
	 * @return el id log intermediacion
	 */
	public Integer getIdLogIntermediacion() {
		return idLogIntermediacion;
	}
	
	/**
	 * Establece el id log intermediacion.
	 *
	 * @param idLogIntermediacion el nuevo id log intermediacion
	 */
	public void setIdLogIntermediacion(Integer idLogIntermediacion) {
		this.idLogIntermediacion = idLogIntermediacion;
	}
	
	/**
	 * Obtiene el solicitud comun.
	 *
	 * @return el solicitud comun
	 */
	public SolicitudComun getSolicitudComun() {
		return solicitudComun;
	}
	
	/**
	 * Establece el solicitud comun.
	 *
	 * @param solicitudComun el nuevo solicitud comun
	 */
	public void setSolicitudComun(SolicitudComun solicitudComun) {
		this.solicitudComun = solicitudComun;
	}
	
	/**
	 * Obtiene el id peticion.
	 *
	 * @return el id peticion
	 */
	public String getIdPeticion() {
		return idPeticion;
	}
	
	/**
	 * Establece el id peticion.
	 *
	 * @param idPeticion el nuevo id peticion
	 */
	public void setIdPeticion(String idPeticion) {
		this.idPeticion = idPeticion;
	}
	
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
	 * Obtiene el resultado.
	 *
	 * @return el resultado
	 */
	public String getResultado() {
		return resultado;
	}
	
	/**
	 * Establece el resultado.
	 *
	 * @param resultado el nuevo resultado
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	/**
	 * Obtiene el descripcion.
	 *
	 * @return el descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Establece el descripcion.
	 *
	 * @param descripcion el nuevo descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public SolicitudComunAuxiliar getSolicitudComunAuxiliar() {
		return solicitudComunAuxiliar;
	}

	public void setSolicitudComunAuxiliar(SolicitudComunAuxiliar solicitudComunAuxiliar) {
		this.solicitudComunAuxiliar = solicitudComunAuxiliar;
	}
	
	
}
