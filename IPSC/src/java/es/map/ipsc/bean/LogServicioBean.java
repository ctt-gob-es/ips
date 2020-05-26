package es.map.ipsc.bean;

import java.util.Date;

import es.map.ips.model.TipoServicio;


/**
 * El Class LogServicioBean.
 */
public class LogServicioBean {
	
	/** el id log servicio. */
	private String idLogServicio;
	
	/** el fecha. */
	private Date fecha;
	
	/** el tipo servicio. */
	private TipoServicio tipoServicio;
	
	/** el id tipo servicio. */
	private String idTipoServicio;
	
	/** el resultado. */
	private String resultado;
	
	/** el tipo error. */
	private String tipoError;
	
	/** el des error. */
	private String desError;
	
	/** el cod error. */
	private String codError;
	
	/** el tiempo respuesta. */
	private Long tiempoRespuesta;
	
	/** el prueba. */
	private String prueba;
	
	/**
	 * Obtiene el id log servicio.
	 *
	 * @return el id log servicio
	 */
	public String getIdLogServicio() {
		return idLogServicio;
	}
	
	/**
	 * Establece el id log servicio.
	 *
	 * @param idLogServicio el nuevo id log servicio
	 */
	public void setIdLogServicio(String idLogServicio) {
		this.idLogServicio = idLogServicio;
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
	 * Obtiene el tipo servicio.
	 *
	 * @return el tipo servicio
	 */
	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}
	
	/**
	 * Establece el tipo servicio.
	 *
	 * @param tipoServicio el nuevo tipo servicio
	 */
	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	
	/**
	 * Obtiene el id tipo servicio.
	 *
	 * @return el id tipo servicio
	 */
	public String getIdTipoServicio() {
		return idTipoServicio;
	}
	
	/**
	 * Establece el id tipo servicio.
	 *
	 * @param idTipoServicio el nuevo id tipo servicio
	 */
	public void setIdTipoServicio(String idTipoServicio) {
		this.idTipoServicio = idTipoServicio;
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
	 * Obtiene el tipo error.
	 *
	 * @return el tipo error
	 */
	public String getTipoError() {
		return tipoError;
	}
	
	/**
	 * Establece el tipo error.
	 *
	 * @param tipoError el nuevo tipo error
	 */
	public void setTipoError(String tipoError) {
		this.tipoError = tipoError;
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
	 * Obtiene el tiempo respuesta.
	 *
	 * @return el tiempo respuesta
	 */
	public Long getTiempoRespuesta() {
		return tiempoRespuesta;
	}
	
	/**
	 * Establece el tiempo respuesta.
	 *
	 * @param tiempoRespuesta el nuevo tiempo respuesta
	 */
	public void setTiempoRespuesta(Long tiempoRespuesta) {
		this.tiempoRespuesta = tiempoRespuesta;
	}
	
	/**
	 * Obtiene el prueba.
	 *
	 * @return el prueba
	 */
	public String getPrueba() {
		return prueba;
	}
	
	/**
	 * Establece el prueba.
	 *
	 * @param prueba el nuevo prueba
	 */
	public void setPrueba(String prueba) {
		this.prueba = prueba;
	}
	
	
}
