package es.map.ipsg.bean;

import java.util.Date;

/**
 * El Class AvisoBean.
 */
public class AvisoBean {
	
	/** el id aviso. */
	private Integer idAviso;
	
	/** el texto. */
	private String texto;
	
	/** el fecha inicio. */
	private Date fechaInicio;
	
	/** el fecha fin. */
	private Date fechaFin;
	
	/** el id estado aviso. */
	private Integer idEstadoAviso;
	
	/** el des estado aviso. */
	private String desEstadoAviso;
	
	/** el num total. */
	private Integer numTotal;
	
	/**
	 * Obtiene el id aviso.
	 *
	 * @return the idAviso
	 */
	public Integer getIdAviso() {
		return idAviso;
	}
	
	/**
	 * Establece el id aviso.
	 *
	 * @param idAviso the idAviso to set
	 */
	public void setIdAviso(Integer idAviso) {
		this.idAviso = idAviso;
	}
	
	/**
	 * Obtiene el texto.
	 *
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}
	
	/**
	 * Establece el texto.
	 *
	 * @param texto the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	/**
	 * Obtiene el fecha inicio.
	 *
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	/**
	 * Establece el fecha inicio.
	 *
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	/**
	 * Obtiene el fecha fin.
	 *
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	
	/**
	 * Establece el fecha fin.
	 *
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Obtiene el id estado aviso.
	 *
	 * @return the idEstadoAviso
	 */
	public Integer getIdEstadoAviso() {
		return idEstadoAviso;
	}
	
	/**
	 * Establece el id estado aviso.
	 *
	 * @param idEstadoAviso the idEstadoAviso to set
	 */
	public void setIdEstadoAviso(Integer idEstadoAviso) {
		this.idEstadoAviso = idEstadoAviso;
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
	 * Obtiene el des estado aviso.
	 *
	 * @return the desEstadoAviso
	 */
	public String getDesEstadoAviso() {
		return desEstadoAviso;
	}
	
	/**
	 * Establece el des estado aviso.
	 *
	 * @param desEstadoAviso the desEstadoAviso to set
	 */
	public void setDesEstadoAviso(String desEstadoAviso) {
		this.desEstadoAviso = desEstadoAviso;
	}
	
	
}
