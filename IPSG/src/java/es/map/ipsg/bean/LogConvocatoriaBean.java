package es.map.ipsg.bean;
// Generated 28-oct-2010 16:36:00 by Hibernate Tools 3.2.4.GA


import java.util.Date;


/**
 * El Class LogConvocatoriaBean.
 */
public class LogConvocatoriaBean  implements java.io.Serializable {


	/** el id log convocatoria. */
	private Integer idLogConvocatoria;
	
	/** el id estado convocatoria anterior. */
	private Integer idEstadoConvocatoriaAnterior;
	
	/** el id estado convocatoria actual. */
	private Integer idEstadoConvocatoriaActual;
	
	/** el id convocatoria. */
	private Long idConvocatoria;
	
	/** el id usuario. */
	private Integer idUsuario;
	
	/** el fecha. */
	private Date fecha;
	
	/** el tipo operacion. */
	private Character tipoOperacion;
	
	/** el detalle. */
	private String detalle;
	
	/** el login. */
	private String login;
	
	/** el num total. */
	private Integer numTotal;
	
	/** el estado convocatoria anterior. */
	private String estadoConvocatoriaAnterior;
	
	/** el estado convocatoria actual. */
	private String estadoConvocatoriaActual;
	
	/** el nombre convocatoria. */
	private String nombreConvocatoria;
	
	/** el fecha formateada. */
	private String fechaFormateada;
	
	/**
	 * Obtiene el id log convocatoria.
	 *
	 * @return el id log convocatoria
	 */
	public Integer getIdLogConvocatoria() {
		return idLogConvocatoria;
	}
	
	/**
	 * Establece el id log convocatoria.
	 *
	 * @param idLogConvocatoria el nuevo id log convocatoria
	 */
	public void setIdLogConvocatoria(Integer idLogConvocatoria) {
		this.idLogConvocatoria = idLogConvocatoria;
	}
	
	/**
	 * Obtiene el id estado convocatoria anterior.
	 *
	 * @return el id estado convocatoria anterior
	 */
	public Integer getIdEstadoConvocatoriaAnterior() {
		return idEstadoConvocatoriaAnterior;
	}
	
	/**
	 * Establece el id estado convocatoria anterior.
	 *
	 * @param idEstadoConvocatoriaAnterior el nuevo id estado convocatoria anterior
	 */
	public void setIdEstadoConvocatoriaAnterior(Integer idEstadoConvocatoriaAnterior) {
		this.idEstadoConvocatoriaAnterior = idEstadoConvocatoriaAnterior;
	}
	
	/**
	 * Obtiene el id estado convocatoria actual.
	 *
	 * @return el id estado convocatoria actual
	 */
	public Integer getIdEstadoConvocatoriaActual() {
		return idEstadoConvocatoriaActual;
	}
	
	/**
	 * Establece el id estado convocatoria actual.
	 *
	 * @param idEstadoConvocatoriaActual el nuevo id estado convocatoria actual
	 */
	public void setIdEstadoConvocatoriaActual(Integer idEstadoConvocatoriaActual) {
		this.idEstadoConvocatoriaActual = idEstadoConvocatoriaActual;
	}
	
	/**
	 * Obtiene el id convocatoria.
	 *
	 * @return el id convocatoria
	 */
	public Long getIdConvocatoria() {
		return idConvocatoria;
	}
	
	/**
	 * Establece el id convocatoria.
	 *
	 * @param idConvocatoria el nuevo id convocatoria
	 */
	public void setIdConvocatoria(Long idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}
	
	/**
	 * Obtiene el id usuario.
	 *
	 * @return el id usuario
	 */
	public Integer getIdUsuario() {
		return idUsuario;
	}
	
	/**
	 * Establece el id usuario.
	 *
	 * @param idUsuario el nuevo id usuario
	 */
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	/**
	 * Obtiene el fecha.
	 *
	 * @return el fecha
	 */
	public Date getFecha() {
		return this.fecha;
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
	public Character getTipoOperacion() {
		return this.tipoOperacion;
	}
	
	/**
	 * Establece el tipo operacion.
	 *
	 * @param tipoOperacion el nuevo tipo operacion
	 */
	public void setTipoOperacion(Character tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	
	/**
	 * Obtiene el detalle.
	 *
	 * @return el detalle
	 */
	public String getDetalle() {
		return this.detalle;
	}
	
	/**
	 * Establece el detalle.
	 *
	 * @param detalle el nuevo detalle
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
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
	 * Obtiene el login.
	 *
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Establece el login.
	 *
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Obtiene el estado convocatoria anterior.
	 *
	 * @return the estadoConvocatoriaAnterior
	 */
	public String getEstadoConvocatoriaAnterior() {
		return estadoConvocatoriaAnterior;
	}

	/**
	 * Obtiene el estado convocatoria actual.
	 *
	 * @return the estadoConvocatoriaActual
	 */
	public String getEstadoConvocatoriaActual() {
		return estadoConvocatoriaActual;
	}

	/**
	 * Obtiene el nombre convocatoria.
	 *
	 * @return the nombreConvocatoria
	 */
	public String getNombreConvocatoria() {
		return nombreConvocatoria;
	}

	/**
	 * Establece el estado convocatoria anterior.
	 *
	 * @param estadoConvocatoriaAnterior the estadoConvocatoriaAnterior to set
	 */
	public void setEstadoConvocatoriaAnterior(String estadoConvocatoriaAnterior) {
		this.estadoConvocatoriaAnterior = estadoConvocatoriaAnterior;
	}

	/**
	 * Establece el estado convocatoria actual.
	 *
	 * @param estadoConvocatoriaActual the estadoConvocatoriaActual to set
	 */
	public void setEstadoConvocatoriaActual(String estadoConvocatoriaActual) {
		this.estadoConvocatoriaActual = estadoConvocatoriaActual;
	}

	/**
	 * Establece el nombre convocatoria.
	 *
	 * @param nombreConvocatoria the nombreConvocatoria to set
	 */
	public void setNombreConvocatoria(String nombreConvocatoria) {
		this.nombreConvocatoria = nombreConvocatoria;
	}

	/**
	 * Obtiene el fecha formateada.
	 *
	 * @return the fechaFormateada
	 */
	public String getFechaFormateada() {
		return fechaFormateada;
	}

	/**
	 * Establece el fecha formateada.
	 *
	 * @param fechaFormateada the fechaFormateada to set
	 */
	public void setFechaFormateada(String fechaFormateada) {
		this.fechaFormateada = fechaFormateada;
	}
	
}


