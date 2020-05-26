package es.map.ipsg.bean;

import java.util.ArrayList;
import java.util.Date;

/**
 * El Class LogServicioBean.
 */
public class LogServicioBean {
	
	/** el id. */
	private Integer id;
	
	/** el fecha. */
	private Date fecha;
	
	/** el id tipo servicio. */
	private Integer idTipoServicio;
	
	/** el des tipo servicio. */
	private String desTipoServicio;
	
	/** el id tipo error. */
	private char idTipoError;
	
	/** el des tipo error. */
	private String desTipoError;
	
	/** el codigo error. */
	private String codigoError;
	
	/** el descripcion error. */
	private String descripcionError;
	
	/** el num total. */
	private Integer numTotal;
	
	/** el tiempo respuesta. */
	private Long tiempoRespuesta;
	
	/** el prueba. */
	private String prueba;
	
	/** el resultado. */
	public String resultado;
	
	/** el solicitudes asociadas. */
	private ArrayList<SolicitudLogErroresBean> solicitudesAsociadas;
	
	/**
	 * Obtiene el id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	
	/**
	 * Establece el fecha.
	 *
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * Obtiene el id tipo servicio.
	 *
	 * @return the idTipoServicio
	 */
	public Integer getIdTipoServicio() {
		return idTipoServicio;
	}
	
	/**
	 * Establece el id tipo servicio.
	 *
	 * @param idTipoServicio the idTipoServicio to set
	 */
	public void setIdTipoServicio(Integer idTipoServicio) {
		this.idTipoServicio = idTipoServicio;
	}
	
	/**
	 * Obtiene el id tipo error.
	 *
	 * @return the idTipoError
	 */
	public char getIdTipoError() {
		return idTipoError;
	}
	
	/**
	 * Establece el id tipo error.
	 *
	 * @param idTipoError the idTipoError to set
	 */
	public void setIdTipoError(char idTipoError) {
		this.idTipoError = idTipoError;
	}
	
	/**
	 * Obtiene el codigo error.
	 *
	 * @return the codigoError
	 */
	public String getCodigoError() {
		return codigoError;
	}
	
	/**
	 * Establece el codigo error.
	 *
	 * @param codigoError the codigoError to set
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}
	
	/**
	 * Obtiene el num total.
	 *
	 * @return the numTotal
	 */
	public Integer getNumTotal() {
		return numTotal;
	}
	
	/**
	 * Establece el num total.
	 *
	 * @param numTotal the numTotal to set
	 */
	public void setNumTotal(Integer numTotal) {
		this.numTotal = numTotal;
	}
	
	/**
	 * Obtiene el des tipo servicio.
	 *
	 * @return the desTipoServicio
	 */
	public String getDesTipoServicio() {
		return desTipoServicio;
	}
	
	/**
	 * Establece el des tipo servicio.
	 *
	 * @param desTipoServicio the desTipoServicio to set
	 */
	public void setDesTipoServicio(String desTipoServicio) {
		this.desTipoServicio = desTipoServicio;
	}
	
	/**
	 * Obtiene el des tipo error.
	 *
	 * @return the desTipoError
	 */
	public String getDesTipoError() {
		return desTipoError;
	}
	
	/**
	 * Establece el des tipo error.
	 *
	 * @param desTipoError the desTipoError to set
	 */
	public void setDesTipoError(String desTipoError) {
		this.desTipoError = desTipoError;
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
	 * Obtiene el solicitudes asociadas.
	 *
	 * @return el solicitudes asociadas
	 */
	public ArrayList<SolicitudLogErroresBean> getSolicitudesAsociadas() {
		return solicitudesAsociadas;
	}
	
	/**
	 * Establece el solicitudes asociadas.
	 *
	 * @param solicitudesAsociadas el nuevo solicitudes asociadas
	 */
	public void setSolicitudesAsociadas(
			ArrayList<SolicitudLogErroresBean> solicitudesAsociadas) {
		this.solicitudesAsociadas = solicitudesAsociadas;
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