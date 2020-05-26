package es.map.ipsc.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * El Class MinisterioBean.
 */
public class MinisterioBean {

	/** el id. */
	private Integer id;
	
	/** el siglas. */
	private String siglas;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el codigo. */
	private String codigo;
	
	/** el url. */
	private String url;
	
	/** el estado. */
	private char estado;
	
	/** el centro gestors. */
	private Set centroGestors = new HashSet(0);
	
	/** el num total. */
	private int numTotal;
	
	
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
	 * Obtiene el num total.
	 *
	 * @return el num total
	 */
	public int getNumTotal() {
		return numTotal;
	}
	
	/**
	 * Establece el num total.
	 *
	 * @param numTotal el nuevo num total
	 */
	public void setNumTotal(int numTotal) {
		this.numTotal = numTotal;
	}
	
	/**
	 * Obtiene el siglas.
	 *
	 * @return el siglas
	 */
	public String getSiglas() {
		return siglas;
	}
	
	/**
	 * Establece el siglas.
	 *
	 * @param pSiglas el nuevo siglas
	 */
	public void setSiglas(String pSiglas) {
		this.siglas = pSiglas;
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
	 * @param pDescripcion el nuevo descripcion
	 */
	public void setDescripcion(String pDescripcion) {
		this.descripcion = pDescripcion;
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
	 * @param pCodigo el nuevo codigo
	 */
	public void setCodigo(String pCodigo) {
		this.codigo = pCodigo;
	}
	
	/**
	 * Obtiene el url.
	 *
	 * @return el url
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Establece el url.
	 *
	 * @param pUrl el nuevo url
	 */
	public void setUrl(String pUrl) {
		this.url = pUrl;
	}
	
	/**
	 * Obtiene el estado.
	 *
	 * @return el estado
	 */
	public char getEstado() {
		return estado;
	}
	
	/**
	 * Establece el estado.
	 *
	 * @param pEstado el nuevo estado
	 */
	public void setEstado(char pEstado) {
		this.estado = pEstado;
	}
	
	/**
	 * Obtiene el centro gestors.
	 *
	 * @return el centro gestors
	 */
	public Set getCentroGestors() {
		return centroGestors;
	}
	
	/**
	 * Establece el centro gestors.
	 *
	 * @param pCentroGestors el nuevo centro gestors
	 */
	public void setCentroGestors(Set pCentroGestors) {
		this.centroGestors = pCentroGestors;
	}

}
