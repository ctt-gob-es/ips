package es.map.ipsg.bean;

import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.Convocatoria;
import es.map.ips.model.SolicitudComun;

/**
 * El Class ProvinciaExamenBean.
 */
public class ProvinciaExamenBean {
	
	/** el id. */
	private Integer id;
	
	/** el codigo. */
	private String codigo;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el estado. */
	private Character estado;
	
	/** el visibilidad. */
	private Boolean visibilidad;
	
	/** el num total. */
	private int numTotal;
	
	/** el solicituds for id provincia examen. */
	private Set<SolicitudComun> solicitudsForIdProvinciaExamen = new HashSet<SolicitudComun>(0);
	
	/** el convocatorias. */
	private Set<Convocatoria> convocatorias = new HashSet<Convocatoria>(0);
	
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
	 * @param descripcion el nuevo descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Obtiene el estado.
	 *
	 * @return el estado
	 */
	public Character getEstado() {
		return estado;
	}
	
	/**
	 * Establece el estado.
	 *
	 * @param estado el nuevo estado
	 */
	public void setEstado(Character estado) {
		this.estado = estado;
	}

	/**
	 * Obtiene el solicituds for id provincia examen.
	 *
	 * @return el solicituds for id provincia examen
	 */
	public Set<SolicitudComun> getSolicitudsForIdProvinciaExamen() {
		return solicitudsForIdProvinciaExamen;
	}
	
	/**
	 * Establece el solicituds for id provincia examen.
	 *
	 * @param solicitudsForIdProvinciaExamen el nuevo solicituds for id provincia examen
	 */
	public void setSolicitudsForIdProvinciaExamen(
			Set<SolicitudComun> solicitudsForIdProvinciaExamen) {
		this.solicitudsForIdProvinciaExamen = solicitudsForIdProvinciaExamen;
	}
	
	/**
	 * Obtiene el convocatorias.
	 *
	 * @return el convocatorias
	 */
	public Set<Convocatoria> getConvocatorias() {
		return convocatorias;
	}
	
	/**
	 * Establece el convocatorias.
	 *
	 * @param convocatorias el nuevo convocatorias
	 */
	public void setConvocatorias(Set<Convocatoria> convocatorias) {
		this.convocatorias = convocatorias;
	}
	
	/**
	 * Obtiene el visibilidad.
	 *
	 * @return el visibilidad
	 */
	public Boolean getVisibilidad() {
		return visibilidad;
	}
	
	/**
	 * Establece el visibilidad.
	 *
	 * @param visibilidad el nuevo visibilidad
	 */
	public void setVisibilidad(Boolean visibilidad) {
		this.visibilidad = visibilidad;
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
	
	
}