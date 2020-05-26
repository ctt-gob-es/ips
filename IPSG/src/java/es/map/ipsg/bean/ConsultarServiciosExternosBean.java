package es.map.ipsg.bean;

import java.util.Date;

/**
 * El Class ConsultarServiciosExternosBean.
 */
public class ConsultarServiciosExternosBean {
	
	/** el fecha desde. */
	private Date fechaDesde;
	
	/** el fecha hasta. */
	private Date fechaHasta;
	
	/** el id servicio. */
	private Integer idServicio;
	
	/** el resultado. */
	private String resultado;
	
	/** el prueba. */
	private char prueba;
	
	/** el direccion. */
	private String direccion;
	
	/** el campo. */
	private String campo;
	
	/** el num registros. */
	private String numRegistros;
	
	/** el pagina actual. */
	private String paginaActual;
	
	/**
	 * Obtiene el fecha desde.
	 *
	 * @return el fecha desde
	 */
	public Date getFechaDesde() {
		return fechaDesde;
	}
	
	/**
	 * Establece el fecha desde.
	 *
	 * @param fechaDesde el nuevo fecha desde
	 */
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	
	/**
	 * Obtiene el fecha hasta.
	 *
	 * @return el fecha hasta
	 */
	public Date getFechaHasta() {
		return fechaHasta;
	}
	
	/**
	 * Establece el fecha hasta.
	 *
	 * @param fechaHasta el nuevo fecha hasta
	 */
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	/**
	 * Obtiene el id servicio.
	 *
	 * @return el id servicio
	 */
	public Integer getIdServicio() {
		return idServicio;
	}
	
	/**
	 * Establece el id servicio.
	 *
	 * @param idServicio el nuevo id servicio
	 */
	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
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
	 * Obtiene el prueba.
	 *
	 * @return el prueba
	 */
	public char getPrueba() {
		return prueba;
	}
	
	/**
	 * Establece el prueba.
	 *
	 * @param prueba el nuevo prueba
	 */
	public void setPrueba(char prueba) {
		this.prueba = prueba;
	}
	
	/**
	 * Obtiene el num registros.
	 *
	 * @return el num registros
	 */
	public String getNumRegistros() {
		return numRegistros;
	}
	
	/**
	 * Establece el num registros.
	 *
	 * @param numRegistros el nuevo num registros
	 */
	public void setNumRegistros(String numRegistros) {
		this.numRegistros = numRegistros;
	}
	
	/**
	 * Obtiene el pagina actual.
	 *
	 * @return el pagina actual
	 */
	public String getPaginaActual() {
		return paginaActual;
	}
	
	/**
	 * Establece el pagina actual.
	 *
	 * @param paginaActual el nuevo pagina actual
	 */
	public void setPaginaActual(String paginaActual) {
		this.paginaActual = paginaActual;
	}
	
	/**
	 * Obtiene el direccion.
	 *
	 * @return el direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	
	/**
	 * Establece el direccion.
	 *
	 * @param direccion el nuevo direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * Obtiene el campo.
	 *
	 * @return el campo
	 */
	public String getCampo() {
		return campo;
	}
	
	/**
	 * Establece el campo.
	 *
	 * @param campo el nuevo campo
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}
		
}
