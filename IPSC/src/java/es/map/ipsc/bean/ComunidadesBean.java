package es.map.ipsc.bean;

/**
 * El Class ComunidadesBean.
 */
public class ComunidadesBean {
	
	/** el id comunidad. */
	private Integer idComunidad;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el codigo. */
	private String codigo;
	
	/** el servicio FN. */
	private boolean servicioFN;
	
	/** el servicio DD. */
	private boolean servicioDD;
	
	/** el titulo req. */
	private boolean tituloReq;
	
	/**
	 * Obtiene el id comunidad.
	 *
	 * @return el id comunidad
	 */
	public Integer getIdComunidad() {
		return idComunidad;
	}
	
	/**
	 * Establece el id comunidad.
	 *
	 * @param idComunidad el nuevo id comunidad
	 */
	public void setIdComunidad(Integer idComunidad) {
		this.idComunidad = idComunidad;
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
	
	/**
	 * Obtiene el codigo.
	 *
	 * @return el codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Establece el codigo.
	 *
	 * @param codigo el nuevo codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Comprueba si es servicio FN.
	 *
	 * @return verdadero, si es servicio FN
	 */
	public boolean isServicioFN() {
		return servicioFN;
	}
	
	/**
	 * Establece el servicio FN.
	 *
	 * @param servicioFN el nuevo servicio FN
	 */
	public void setServicioFN(boolean servicioFN) {
		this.servicioFN = servicioFN;
	}
	
	/**
	 * Comprueba si es servicio DD.
	 *
	 * @return verdadero, si es servicio DD
	 */
	public boolean isServicioDD() {
		return servicioDD;
	}
	
	/**
	 * Establece el servicio DD.
	 *
	 * @param servicioDD el nuevo servicio DD
	 */
	public void setServicioDD(boolean servicioDD) {
		this.servicioDD = servicioDD;
	}
	
	/**
	 * Comprueba si es titulo req.
	 *
	 * @return verdadero, si es titulo req
	 */
	public boolean isTituloReq() {
		return tituloReq;
	}
	
	/**
	 * Establece el titulo req.
	 *
	 * @param tituloReq el nuevo titulo req
	 */
	public void setTituloReq(boolean tituloReq) {
		this.tituloReq = tituloReq;
	}

}
