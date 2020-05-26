package es.map.ipsg.bean;

import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.SolicitudComun;



/**
 * El Class EstadoSolicitudBean.
 */
public class EstadoSolicitudBean implements java.io.Serializable {

	/** el id. */
	private Integer id;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el tipo solicitud. */
	private Character tipoSolicitud;
	
	/** el solicituds. */
	private Set<SolicitudComun> solicituds = new HashSet<SolicitudComun>(0);

	
	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public Integer getId() {
		return this.id;
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
	 * @param descripcion el nuevo descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	/**
	 * Obtiene el tipo solicitud.
	 *
	 * @return el tipo solicitud
	 */
	public Character getTipoSolicitud() {
		return this.tipoSolicitud;
	}

	/**
	 * Establece el tipo solicitud.
	 *
	 * @param tipoSolicitud el nuevo tipo solicitud
	 */
	public void setTipoSolicitud(char tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	/**
	 * Obtiene el solicituds.
	 *
	 * @return el solicituds
	 */
	public Set<SolicitudComun> getSolicituds() {
		return this.solicituds;
	}

	/**
	 * Establece el solicituds.
	 *
	 * @param solicituds el nuevo solicituds
	 */
	public void setSolicituds(Set<SolicitudComun> solicituds) {
		this.solicituds = solicituds;
	}

}
