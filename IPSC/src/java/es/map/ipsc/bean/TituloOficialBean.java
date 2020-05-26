package es.map.ipsc.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * El Class TituloOficialBean.
 */
public class TituloOficialBean {

	/** el id. */
	private Integer id;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el estado. */
	private char estado;
	
	/** el convocatorias. */
	private Set convocatorias = new HashSet(0);
	
	/** el solicituds. */
	private Set solicituds = new HashSet(0);
	
	/** el codigo. */
	private String codigo;


	

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
	 * @param pColicituds el nuevo solicituds
	 */
	public void setSolicituds(Set pColicituds) {
		this.solicituds = pColicituds;
	}

}
