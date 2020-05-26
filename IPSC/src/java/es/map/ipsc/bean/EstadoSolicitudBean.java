package es.map.ipsc.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * El Class EstadoSolicitudBean.
 */
public class EstadoSolicitudBean {
	
	/** el id. */
	private Integer id;
	
	/** el descripcion. */
	private String descripcion;
	
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
	 * Obtiene el solicituds.
	 *
	 * @return el solicituds
	 */
	public Set getSolicituds() {
		return solicituds;
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
