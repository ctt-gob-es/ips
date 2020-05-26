package es.map.ipsg.bean;

import java.util.Date;

import es.map.ips.model.Usuario;

/**
 * El Class LogGenericoBean.
 */
public class LogGenericoBean {
	
	/** el id. */
	private Integer id;
	
	/** el usuario. */
	private Usuario usuario;
	
	/** el fecha. */
	private Date fecha;
	
	/** el tipo operacion. */
	private Character tipoOperacion;
	
	/** el tipo funcionalidad. */
	private String tipoFuncionalidad;
	
	/** el detalle operacion. */
	private String detalleOperacion;
	
	/** el id tabla origen. */
	private Long idTablaOrigen;
	
	/** el login. */
	private String login;
	
	/** el descripcion tipo operacion. */
	private String descripcionTipoOperacion;
	
	/** el num total. */
	private Integer numTotal;
	
	/** el centro gestor. */
	private String centroGestor;
	
	/** el fecha formateada. */
	private String fechaFormateada;
	
	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el usuario.
	 *
	 * @return el usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	
	/**
	 * Establece el usuario.
	 *
	 * @param usuario el nuevo usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
	 * Obtiene el tipo operacion.
	 *
	 * @return el tipo operacion
	 */
	public Character getTipoOperacion() {
		return tipoOperacion;
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
	 * Obtiene el tipo funcionalidad.
	 *
	 * @return el tipo funcionalidad
	 */
	public String getTipoFuncionalidad() {
		return tipoFuncionalidad;
	}
	
	/**
	 * Establece el tipo funcionalidad.
	 *
	 * @param tipoFuncionalidad el nuevo tipo funcionalidad
	 */
	public void setTipoFuncionalidad(String tipoFuncionalidad) {
		this.tipoFuncionalidad = tipoFuncionalidad;
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
	 * Obtiene el id tabla origen.
	 *
	 * @return el id tabla origen
	 */
	public Long getIdTablaOrigen() {
		return idTablaOrigen;
	}
	
	/**
	 * Establece el id tabla origen.
	 *
	 * @param idTablaOrigen el nuevo id tabla origen
	 */
	public void setIdTablaOrigen(Long idTablaOrigen) {
		this.idTablaOrigen = idTablaOrigen;
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
	 * Obtiene el descripcion tipo operacion.
	 *
	 * @return the descripcionTipoOperacion
	 */
	public String getDescripcionTipoOperacion() {
		return descripcionTipoOperacion;
	}
	
	/**
	 * Establece el descripcion tipo operacion.
	 *
	 * @param descripcionTipoOperacion the descripcionTipoOperacion to set
	 */
	public void setDescripcionTipoOperacion(String descripcionTipoOperacion) {
		this.descripcionTipoOperacion = descripcionTipoOperacion;
	}
	
	/**
	 * Obtiene el centro gestor.
	 *
	 * @return the centroGestor
	 */
	public String getCentroGestor() {
		return centroGestor;
	}
	
	/**
	 * Establece el centro gestor.
	 *
	 * @param centroGestor the centroGestor to set
	 */
	public void setCentroGestor(String centroGestor) {
		this.centroGestor = centroGestor;
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