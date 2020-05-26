package es.map.ipsc.bean;

import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.CuerpoEscala;


/**
 * El Class EspecialidadBean.
 */
public class EspecialidadBean {
	
	/** el id. */
	private Integer id;
	
	/** el cuerpo escala. */
	private CuerpoEscala cuerpoEscala;
	
	/** el codigo. */
	private String codigo;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el estado. */
	private char estado;
	
	/** el convocatorias. */
	private Set convocatorias = new HashSet(0);
	
	/** el solicituds. */
	private Set solicituds = new HashSet(0);


	

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
	 * Obtiene el cuerpo escala.
	 *
	 * @return el cuerpo escala
	 */
	public CuerpoEscala getCuerpoEscala() {
		return this.cuerpoEscala;
	}

	/**
	 * Establece el cuerpo escala.
	 *
	 * @param pCuerpoEscala el nuevo cuerpo escala
	 */
	public void setCuerpoEscala(CuerpoEscala pCuerpoEscala) {
		this.cuerpoEscala = pCuerpoEscala;
	}

	/**
	 * Obtiene el codigo.
	 *
	 * @return el codigo
	 */
	public String getCodigo() {
		return this.codigo;
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
	 * Obtiene el descripcion.
	 *
	 * @return el descripcion
	 */
	public String getDescripcion() {
		return this.descripcion;
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
	 * Obtiene el estado.
	 *
	 * @return el estado
	 */
	public char getEstado() {
		return this.estado;
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
	 * Obtiene el convocatorias.
	 *
	 * @return el convocatorias
	 */
	public Set getConvocatorias() {
		return this.convocatorias;
	}

	/**
	 * Establece el convocatorias.
	 *
	 * @param pConvocatorias el nuevo convocatorias
	 */
	public void setConvocatorias(Set pConvocatorias) {
		this.convocatorias = pConvocatorias;
	}

	/**
	 * Obtiene el solicituds.
	 *
	 * @return el solicituds
	 */
	public Set getSolicituds() {
		return this.solicituds;
	}

	/**
	 * Establece el solicituds.
	 *
	 * @param pSolicituds el nuevo solicituds
	 */
	public void setSolicituds(Set pSolicituds) {
		this.solicituds = pSolicituds;
	}

}
