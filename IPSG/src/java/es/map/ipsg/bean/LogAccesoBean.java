package es.map.ipsg.bean;

import java.util.Date;

/**
 * El Class LogAccesoBean.
 */
public class LogAccesoBean {
	
	/** el id. */
	private Integer id;
	
	/** el login usuario. */
	private String loginUsuario;
	
	/** el fecha. */
	private Date fecha;
	
	/** el id codigo acceso. */
	private Integer idCodigoAcceso;
	
	/** el num total. */
	private Integer numTotal;
	
	/** el resultado acceso. */
	private String resultadoAcceso;
	
	/** el fecha formateada. */
	private String fechaFormateada;
	
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
	 * @param id el nuevo id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el login usuario.
	 *
	 * @return el login usuario
	 */
	public String getLoginUsuario() {
		return loginUsuario;
	}
	
	/**
	 * Establece el login usuario.
	 *
	 * @param loginUsuario el nuevo login usuario
	 */
	public void setLoginUsuario(String loginUsuario){
		this.loginUsuario = loginUsuario;
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
	 * Obtiene el id codigo acceso.
	 *
	 * @return el id codigo acceso
	 */
	public Integer getIdCodigoAcceso() {
		return idCodigoAcceso;
	}
	
	/**
	 * Establece el id codigo acceso.
	 *
	 * @param idCodigoAcceso el nuevo id codigo acceso
	 */
	public void setIdCodigoAcceso(Integer idCodigoAcceso) {
		this.idCodigoAcceso = idCodigoAcceso;
	}
	
	/**
	 * Obtiene el resultado acceso.
	 *
	 * @return the resultadoAcceso
	 */
	public String getResultadoAcceso() {
		return resultadoAcceso;
	}
	
	/**
	 * Establece el resultado acceso.
	 *
	 * @param resultadoAcceso the resultadoAcceso to set
	 */
	public void setResultadoAcceso(String resultadoAcceso) {
		this.resultadoAcceso = resultadoAcceso;
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
